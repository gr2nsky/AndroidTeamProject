# AndroidTeamProject
> ### Tool โ๏ธ <br>
Android Studio 4.1.3 <br>
<br>

> ### Server ๐ซ <br>
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

> ### Permisson<br>
INTERNET<br>
ACCESS_NETWORK_STATE<br>
READ_EXTERNAL_STORAGE<br>
CALL_PHONE<br>
<br>


> ### Participants<br>

์ค์ฌํ ๐ , ์ํ์ง ๐-shop list , ์ต์ง์ฐ DB ๋ด๋น ๐ , ์ค์์ค ๐<br>
<br>

> ## Naming Rules<br>
<p>Activity ๋ฐ class๋ ํ์ค์นผ ํ๊ธฐ๋ฒ์ ์ฌ์ฉํฉ๋๋ค.</p>
<p>Activity์ ๊ฒฝ์ฐ ์ด๋ฆ ๋ค์ Activity๋ฅผ ๋ถ์ฌ ๊ตฌ๋ถ๋๋๋ก ํฉ๋๋ค.</p>
<p>Layout์ ์ค๋ค์ดํฌ ํ๊ธฐ๋ฒ์ ์ฌ์ฉํ๋, ํ์_์์น_๊ธฐ๋ฅ ์๊ณผ ๊ฐ์ด ํฐ ๋ฒ์ฃผ์ ์์ฑ๋ถํฐ ์ฐจ๋ก๋ก ์๋ ฅํฉ๋๋ค.</p>
<p>apache tomcat server์ jspํ์ผ์ ๊ฒฝ์ฐ ์นด๋ฉํ๊ธฐ๋ฒ์ ์ฌ์ฉํฉ๋๋ค.</p><br>

> ## Common File Usage Rules<br>
### ShareVar.class <br>
<p>hostIP๋ ๋ณธ์ธ์ ์ธํฐ๋ท ํ๊ฒฝ์ ๋ง๊ฒ ์์ ํด์ ์ฌ์ฉํ๋๋ก ํฉ๋๋ค. </p>
<p>๋จ, hostRootAddr๋ tomcat server์ root dir์ด๋ฏ๋ก ์์ ์ ์ ๋ ๋ถํํฉ๋๋ค. </p>
<p>์ด์ธ์ ์ถ๊ฐ ๋ฐ ์์ ์ ๊ฐ๋ฅํ๋ ํผ์๋ง ์ฌ์ฉํ๋ ๋ณ์์ ๊ณต์ ๋ ์ง์ํ๊ฒ ์ต๋๋ค. </p>
<br>
```java
public static String hostIP = "192.168.2.4";
public static String hostRootAddr = "http://" + hostIP + ":8080/MogaStyle/";
```
