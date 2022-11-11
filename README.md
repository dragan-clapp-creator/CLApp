# CLApp
CLApp stays for **Cellular Language Approach**.<br>
CLApp can be seen as a script language but also as an actors language.

As it relies on java, it may easily interact with it by simply calling java methods. It can even add some _byte code injection_ to those java classes before they will be called.
Therefore the following _Byte Code Engineering Library_ **BCEL-5.2.JAR** is used.

To run it, assuming your source is written in a _test.clp_ file and a bcel jar is under a _/lib_ folder, type the following :

_**java -cp "lib/bcel-5.2.jar;lib/clapp.jar" clapp.run.Supervisor test.clp**_

If you are not familiar with the CLApp syntax, please refer to the **Visual CLApp Editor** which will help you creating CLApp projects, and much more, in an intuitive way.
