# sales-taxes

This repository contains a maven project which is aimed solve the [sales taxes problem](https://github.com/xpeppers/sales-taxes-problem/blob/master/README.md). 

To use this application, first clone the repository to your local machine. 
Then from inside the sales taxes folder run the following command to ge the jar file.

```bash
mvn package
```

to execute the tests run the following command 
```bash
mvn test
```

to use the application run the following command
```bash
java -cp target/sales-taxes-1.0-SNAPSHOT.jar io.github.brukt.App
```

in the command prompt enter the file names where the input is saved and where you want to save the output text as shown below
```
bruk@bruk-pc:sales-taxes$java -cp target/sales-taxes-1.0-SNAPSHOT.jar io.github.brukt.App
Enter the input file path
data/input1.txt
Enter the output file path
data/ouput1.txt
The receipt is printed in: data/ouput1.txt
```

The content of the input file shuld only contain items and their shelf price. 
example *input1.txt*.
```
2 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
```
