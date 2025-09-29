@echo off
setlocal
pushd "%~dp0.."
if not exist "gradlew.bat" gradle wrapper
call gradlew.bat :target_awt_desktop:jar %*
set EXITCODE=%ERRORLEVEL%
popd
exit /b %EXITCODE%