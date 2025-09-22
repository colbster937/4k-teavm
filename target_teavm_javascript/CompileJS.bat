@echo off
setlocal

pushd "%~dp0.."

if not exist "gradlew.bat" (
    gradle wrapper
    if errorlevel 1 (
        popd
        exit /b 1
    )
)

call gradlew.bat :target_teavm_javascript:generateJavaScript %*
set EXITCODE=%ERRORLEVEL%

popd
exit /b %EXITCODE%
