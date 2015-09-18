## ItelliJ IDEA Community Edition 설치 직후 설정

* IntelliJ IDEA 를 실행합니다. "Complete Installation" 대화 상자가 표시되면 아무 것도 변경하지 않고 "OK"버튼을 클릭합니다.
* "Welcome to IntelliJ IDEA" 화면으로 돌아갑니다. 화면 하단의 Configure -> Project Defaults -> Project Structure를 선택합니다.
* "Project Structure" 대화 상자가 표시됩니다. 다음 설정을 한 후 "OK"버튼을 클릭합니다.
* Project Settings -> Project -> "Project SDK "의 "New ... " -> " JDK "을 선택한 후 표시된 "Select Home Directory for JDK " 대화 상자에서 JDK가 설치된 경로를 선택하고 "OK"버튼 를 클릭합니다.
* Project language level 에서 8-Lambdas, type annotations etc. 를 선택합니다.


## IntelliJ IDEA 에서 프로젝트 생성

* IntelliJ IDEA를 시작합니다.
* 화면 중앙의 "Create New Project"를 클릭합니다.
* "New Project" 화면이 표시됩니다. 화면 왼쪽 목록에서 "Gradle"를 선택합니다.
* 화면 오른쪽에 Gradle Project 설정 화면이 표시됩니다. "Create directories for empty content roots automatically"을 체크한 후 "Next"버튼을 클릭합니다. (프로젝트의 src 디렉토리가 자동으로 생성됩니다)
* 프로젝트 이름과 디렉토리를 입력하는 화면이 표시됩니다. 값을 입력 후 "Finish"버튼을 클릭합니다. "Directory Does Not Exist" 대화 상자가 나타나면 "OK"버튼을 클릭합니다.
* IntelliJ IDEA의 메인 화면이 표시됩니다.

## Spring Boot + Mustache 기본 설정

* build.gradle 파일을 열어서 아래의 내용으로 변경합니다.
```gradle
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.2.0.RELEASE")
        classpath("org.springframework:springloaded:1.2.1.RELEASE")
    }
}

apply plugin: 'java'
apply plugin: 'spring-boot'
apply plugin: 'idea'

jar {
    baseName = 'spring-boot-sample'
    version =  '0.0.1-SNAPSHOT'
}

idea {
    module {
        inheritOutputDirs = false
        outputDir = file("$buildDir/classes/main/")
    }
}

repositories {
    jcenter()
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web:1.2.0.RELEASE")
    compile("org.springframework.boot:spring-boot-starter-mustache:1.2.5.RELEASE")
    compile("org.springframework.boot:spring-boot-devtools")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
```
* 화면 왼쪽의 Project View에서 다음 계층에 있는 java 를 선택한 후 적절한 패키지를 생성한 뒤, 생성된 패키지에서 "New" -> "Java Class" 를 선택합니다. "Create New Class" 대화 상자가 나타나면 Application 입력 한 후 "OK" 버튼을 클릭합니다.
* Application.java 파일을 열어서 아래의 내용으로 변경합니다.
```java
package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
* 위에서 생성한 패키지의 하위에 web 패키지를 생성하고, "New" -> "Java Class" 를 선택합니다. "Create New Class" 대화 상자가 나타나면 TestController 입력 한 후 "OK" 버튼을 클릭합니다.
* TestController.java 파일을 열어서 아래의 내용으로 변경합니다.
```java
package example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "this is '/test' page.";
    }

    @RequestMapping("/json/{name}")
    @ResponseBody
    public Person json(@PathVariable String name) {
        Person person = new Person();
        person.setName(name);
        person.setAge(50);
        return person;
    }

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Hello World");
        model.put("title", "Hello App");
        return "home";
    }
}
```
* web 패키지에서 "New" -> "Java Class" 를 선택합니다. "Create New Class" 대화 상자가 나타나면 Person 입력 한 후 "OK" 버튼을 클릭합니다.
* Person.java 파일을 열어서 아래의 내용으로 변경합니다.
```java
package example.web;

public class Person {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
```
* resources 디렉토리 아래에 templates 디렉토리를 만들고 여기에 아래 두 개의 HTML 파일을 생성합니다.

**home.html**
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>

<body>
    Title: {{title}}
    <br/>
    Message: {{message}}
    <br/>
    time: {{time}}
</body>
</html>
```

**error.html**
```html
<!DOCTYPE html>
<html lang="en">
<body>
   Something went wrong: {{status}} {{error}}
</body>
</html>
```
* gradle 빌드를 한 후, Application 을 실행하면 localhost:8080 으로 웹서버에 연결할 수 있습니다.
