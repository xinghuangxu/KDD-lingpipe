KDD-lingpipe
============

Use lingpipe to do sentiment analysis

How to run the sentiment analysis on aspect-level.
Run on eclipse:
1. You need a plugin called "egit" to clone the repository to your local machine.
2. After the cloning, import the project into your workspace
3. You need to add junit-4.8.2.jar into your project java build path. Then Compile the project. 
4. Run the sentiment analysis using the main class in xinghuangxu.lingpipe.snipeet.extractor -> Extractor -> main
 Command: - l + r   
l and r are the left and right size of the snipeet. 
For example: if [ - 5 + 5 ] is input, snipeet will look like 
[w1 w2 w3 w4 w5 target w1 w2 w3 w4 w5]
5. Put your data into the Review_DB folder which will be created under .../xinghuangxu.lingpipe/Sentiment_DB/ the first time you run the program.
For example, if you want to get the sentiment of camera. You can create a folder named 
Camera and put all the xml review filesa about that camera inside the folder. After that you also need to add a aspect file 
inside this folder. 
A example of aspect file:
<Camera><Screen>Screen|LED</Screen><Images>Image|Quality|Prints|Color|Clarity|Pictures|Photos|Snapshots|Indoor shots|Outdoor shots </Images><Battery>Batter</Battery><Zoom>Zoom|Digital zoom</Zoom><Features>Feature|Setting|Auto</Features><Price>Price</Price></Camera>
6. After that you can run the extractor and you will be able to find the results inside the .../xinghuangxu.lingpipe/Sentiment_DB/Log_DB folder.
 

Run in JVM:
1. Download the project from the repository, install java 7(JVM)
2. open your terminal, navigate to the project directory ../xinghuangxu.lingpipe/
3. input command: java -jar kdd-lingpipe-sentiment.jar - l - r
4. Put your data into the Review_DB folder which will be created under .../xinghuangxu.lingpipe/Sentiment_DB/ the first time you run the program.
For example, if you want to get the sentiment of camera. You can create a folder named 
Camera and put all the xml review filesa about that camera inside the folder. After that you also need to add a aspect file 
inside this folder. 
A example of aspect file:
<Camera><Screen>Screen|LED</Screen><Images>Image|Quality|Prints|Color|Clarity|Pictures|Photos|Snapshots|Indoor shots|Outdoor shots </Images><Battery>Batter</Battery><Zoom>Zoom|Digital zoom</Zoom><Features>Feature|Setting|Auto</Features><Price>Price</Price></Camera>
5. input command: java -jar kdd-lingpipe-sentiment.jar - l - r
6. After that you can run the extractor and you will be able to find the results inside the .../xinghuangxu.lingpipe/Sentiment_DB/Log_DB folder.
 