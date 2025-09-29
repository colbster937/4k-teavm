@echo off
setlocal
pushd "%~dp0.."
if not exist "gradlew.bat" gradle wrapper
call gradlew.bat :target_teavm_javascript:generateJavaScript %*
set EXITCODE=%ERRORLEVEL%
popd
exit /b %EXITCODE%