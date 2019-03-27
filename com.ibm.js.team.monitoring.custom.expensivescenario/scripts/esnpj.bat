rem set JAVA_HOME=C:\PROGRA~1\Java\jre1.8.0_181
set PLAIN_JAVA=C:\RTC606Dev\Installs\PlainJavaAPI

"%JAVA_HOME%\bin\java" -Djava.ext.dirs="./lib;%PLAIN_JAVA%;%JAVA_HOME%/jre/lib/ext" -cp "%PLAIN_JAVA%;./lib;./esnpj_lib" -jar esnpj.jar %*