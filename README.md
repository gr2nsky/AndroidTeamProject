# AndroidTeamProject
> ### Tool<br>
Android Studio 4.1.3 <br>
<br>

> ### Server<br>
Apache Tomcat 8.5.65.0<br>
<br>

> ### Language<br>
java, jsp<br>

<br>

> ### Database<br>
MySQL 8.0.23<br>
sub)mysql-workbench-community-8.0.17<br>

<br>

> ### API<br>
...<br>

<br>


> ### Library<br>
cos.jar<br>

<br>

> ### Participants<br>
윤재필, 임현진, 최지연, 오영준<br>
<br>

> ## Naming Rules<br>
Activity 및 class는 파스칼 표기법을 사용합니다.<br>
Activity의 경우 이름 뒤에 Activity를 붙여 구분되도록 합니다.<br>
Layout은 스네이크 표기법을 사용하되, 타입_위치_기능 순과 같이 큰 범주의 속성부터 차례로 입력합니다.<br>
apache tomcat server의 jsp파일의 경우 카멜표기법을 사용합니다.<br>

> ## Common File Usage Rules<br>
### ShareVar.class <br>
hostIP는 본인의 인터넷 환경에 맞게 수정해서 사용하도록 합니다. <br>
단, hostRootAddr는 tomcat server의 root dir이므로 수정은 절대 불허합니다. <br>
이외의 추가 및 수정은 가능하나 혼자만 사용하는 변수의 공유는 지양하겠습니다. <br>
```java
public static String hostIP = "192.168.2.4";
public static String hostRootAddr = "http://" + hostIP + ":8080/MogaStyle/";
```
