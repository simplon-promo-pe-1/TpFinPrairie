del footballAmericain.war
rd /s /q temp
md temp
xcopy /E src\main\webapp\* temp
jar cvf footballAmericain.war -C temp .