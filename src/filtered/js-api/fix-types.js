const fs = require('fs');
const path = require('path');
const glob = require('glob');

console.log('Fixing type definition imports...');

// Find all .ts and .d.ts files in the model/v5 directory
const files = glob.sync('model/**/*.{ts,d.ts}');

files.forEach(file => {
    console.log(`Processing ${file}...`);
    let content = fs.readFileSync(file, 'utf8');
    
    // Remove HTTP imports
    content = content.replace(/import.*from ['"]\.\.\/http\/http['"];?\n?/g, '');
    
    // Fix relative imports
    content = content.replace(/from ['"]\.\.\/v5\//g, 'from \'./');
    
    fs.writeFileSync(file, content);
});

console.log('Type definition imports fixed successfully!'); 