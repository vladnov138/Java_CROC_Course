# Приложение, считывающий частоту появления каждого символа в указанном текстовом файле и выводящее это в отдельный текстовый файл

## Сборка проекта
1. Сборка проекта с помощью Apache Maven

Сборка с выполнением тестов и упаковкой полученного приложения в JAR файл:
``` 
mvn clean package 
```

Только компиляция исходного кода приложения:
``` 
mvn clean compile
```

2. Сборка проекта в IDE

## Запуск приложения
Собранное приложение помещается в JAR файл **target/character-count-1.0.0-SNAPSHOT.jar**.
Запуск:
```
java -jar target/character-counts-1.0.0-SNAPSHOT.jar <путь к текстовому файлу для чтения> <путь к текстовому файлу для вывода результата>
```
__Примечание: если аргументов нет, путь укажется по умолчанию__