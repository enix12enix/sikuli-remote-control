# Sikuli Remote Control

SikuliRC server and java wrapped client API
Invoke sikuli function remotely as SeleniumRC, and you can use it with Selenium or webdriver for your web automation.

## System Requirements
* Sikuli-X-1.0rc3 (r905)
* Java6

### Downloads
* Sikuli server dowland page. http://sourceforge.net/projects/sikulircserver/files/

## Usage

Run sikuli server on mac:

    java -jar server-mac-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Run sikuli server on windows:
    
    java -jar server-windows-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Add java client api jar to your class path:

    RemoteScreen rs = new RemoteScreen("localhost");
    rs.setMinSimilarity(0.9);
    rs.click("D://test.png");
    rs.click("http://example.org/test.png");
    rs.find("D://test.png");
    rs.find("http://example.org/test.png");
    rs.doubleClick("D://test.png");
    rs.doubleClick("http://example.org/test.png");
    rs.waitUntil("D://test.png", 5000);

There is a ruby wrapped API here: http://github.com/enix12enix/sikulirc

## How to build it

You need maven2 to build it. Not sure maven3 works properly, but you can try.

Sikuli-script.jar is not in official maven repository, so we need install it ourself.

Get sikuli-script.jar of windows and mac, rename them 'sikuli-script-mac.jar' and 'sikuli-script-windows.jar'.

Also you can download them from http://sourceforge.net/projects/sikulircserver/files/sikuli-script-1.0rc3%28r905%29/

Then install sikuli-script.jar

    mvn install:install-file -Dfile=sikuli-script-mac.jar -DgroupId=org.sikuli.script -DartifactId=sikuli-script-mac -Dversion="X 1.0 rc3" -Dpackaging=jar
 
    mvn install:install-file -Dfile=sikuli-script-windows.jar -DgroupId=org.sikuli.script -DartifactId=sikuli-script-windows -Dversion="X 1.0 rc3" -Dpackaging=jar

To build project

    mvn install

## Resources

|||
|-----------------------------------:|:--------------------------|
|                              **Sikuli Home Page**: | http://sikuli.org/ |
|     **Sikuli Remote Control Github Project Page**: | http://github.com/enix12enix/sikuli-remote-control |
|  **Sikuli ReMote Control Ruby Wrapped Client API Github Project Page**: | http://github.com/enix12enix/sikulirc |








