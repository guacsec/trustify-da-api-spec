#!/bin/bash

set -euo pipefail

RED='\033[0;31m' # Red Color
GREEN='\033[0;32m' # Green Color
NC='\033[0m' # No Color

print_message() {
    echo -e "${GREEN}${1}${NC}"
}

remove_snapshot() {
    print_message "Removing snapshot from pom"
    mvn -B versions:set -DremoveSnapshot -DgenerateBackupPoms=false
    mvn -B clean package
}

git_push() {
    remotes = `git remote`
    expected_remotes=("upstream" "origin")
    for remote in "${remotes[@]}"; do
        if [[ ${expected_remotes[@]} =~ $remote ]]; then
            git push ${remote} ${1}
        fi
    done
}

git_commit() {
    print_message "Committing messages"
    git add .
    git commit -sm "build(release): $1"
    git_push main
}

git_tag() {
    version=`mvn help:evaluate -Dexpression=project.version -q -DforceStdout`
    print_message "Creating tag v${version}"
    git tag v${version}
    git_push v${version}
}

next_version() {
    version=${1:-}
    if [[ ! -z $version ]]; then
        echo "Using custom version ${version}"
        mvn -B release:update-versions -DnewVersion=${version}
    else
        echo "Using default version"
        mvn -B release:update-versions
    fi
}

perform_release() {
    remove_snapshot
    git_commit "removed snapshot from version"
    git_tag

    next_version ${1:-}
    mvn -B clean verify
    git_commit "updated next development version"
}

branch=`git branch --no-color --show-current`
if [ "main" != $branch ]; then
 echo -e "${RED}Error: The release must be performed in the main branch. Current: ${branch}"
 exit 1
fi

if [ ! -z  "$(git status --porcelain)"]; then
  echo -e "${RED}Error: Make sure all changes are committed"
  exit 1
fi

print_message "Performing release"
perform_release ${1:-''}
