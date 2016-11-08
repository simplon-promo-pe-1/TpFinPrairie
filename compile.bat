rd /s /q src\main\webapp\WEB-INF\classes
md src\main\webapp\WEB-INF\classes
javac -cp "src\main\java;src\main\webapp\WEB-INF\lib/*" -d src\main\webapp\WEB-INF\classes src\main\java\co\simplon\prairie\*.java
copy src\main\resources\* src\main\webapp\WEB-INF\classes