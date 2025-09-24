@echo off
if exist javascript_alt (
    rmdir /s /q javascript_alt
)
mkdir javascript_alt
copy /y javascript\alt2.html javascript_alt\index.html
copy /y javascript\manifest_alt2.json javascript_alt\manifest_alt2.json
copy /y javascript\icon.png javascript_alt\icon.png
copy /y javascript\classes.js javascript_alt\classes.js
copy /y javascript\Minecraft4k_alt2.html javascript_alt\4k.html
