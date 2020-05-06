call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo Crud application deployment has errors
goto fail

:openbrowser
cd "C:\Program Files (x86)\Google\Chrome\Application"
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open browser

:fail
echo.
echo There were errors

:end
echo.
echo Show tasks script is finished