# Gerando o APK do projeto

Para gerar o APK, abra um terminal na raiz do projeto e execute o seguinte comando:

	./gradlew assembleDebug

Esse comando vai criar um APK no seguinte diretório:

	/app/build/outputs/apk/debug/

Para instalar o APK com o ADB, executar o seguinte comando:

	adb install app-debug.apk
