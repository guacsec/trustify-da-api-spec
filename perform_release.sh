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
    mvn deploy -Ppublish -Prelease
}

git_push() {
    for remote in `git remote`; do
        if [ "upstream" = ${remote} ]; then
            print_message "push to ${remote} ${1}"
            git push ${remote} ${1}
        fi
    done
}

git_commit() {
    print_message "Commit: $1"
    git add .
    git commit -sm "build(release): $1"
    git_push main
}

git_tag() {
    version=`mvn help:evaluate -Dexpression=project.version -q -DforceStdout`
    print_message "Creating tag v${version}"
    git tag -s v${version} -m "v${version}"
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
    mvn -B package -Ppublish
    git_commit "updated next development version"
}

branch=`git branch --no-color --show-current`
if [ "main" != $branch ]; then
 echo -e "${RED}Error: The release must be performed in the main branch. Current: ${branch}"
 exit 1
fi

if [ ! -z  "$(git status --porcelain)" ]; then
  echo -e "${RED}Error: Make sure all changes are committed"
  exit 1
fi

print_message "Performing release"
perform_release ${1:-''}
