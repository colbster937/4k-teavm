@echo off
if exist dist\mmc_instance.zip del /f /q dist\mmc_instance.zip
if not exist dist mkdir dist >nul
if not exist build mkdir build >nul
xcopy ..\src\mmc_instance build\mmc /E /I /Y >nul
copy /Y ..\src\main\resources\icon.png build\mmc\icon.png >nul
powershell -NoProfile -Command "Compress-Archive -Path 'build\mmc\*' -DestinationPath 'dist\mmc_instance.zip' -Force"
rmdir /S /Q build\mmc
rmdir /S /Q build