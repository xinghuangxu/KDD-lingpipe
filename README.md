KDD-lingpipe
============

Use lingpipe to do sentiment analysis<br>

How to run and configure the sentiment analysis on aspect-level.<br>


<table>
<caption>Folder Structure:</caption>
<th colspan="3">Sentiment_DB</th>
<tr>
<td>Training_DB</td>
<td>Review_DB</td>
<td>Log_DB</td>
</tr>
</table>

<ul>
<li>Run on eclipse:
<ol>
<li> You need a plugin called "egit" to clone the repository to your local machine.
<li> After the cloning, import the project into your workspace
<li> You need to add junit-4.8.2.jar into your project java build path. Then Compile the project. 
<li>  Run the sentiment analysis using the main class in xinghuangxu.lingpipe.snipeet.extractor -> Extractor -> main
 Command: <code>- l + r</code>   
l and r are the left and right size of the snipeet. 
For example: if [ - 5 + 5 ] is input, snipeet will look like 
[w1 w2 w3 w4 w5 target w1 w2 w3 w4 w5]
<li> If you want to run a new group of data. <br>
Put your data into the Review_DB folder which will be created under <strong>.../xinghuangxu.lingpipe/Sentiment_DB/</strong> the first time you run the program.
<p>For example, if you want to get the sentiment of camera. You can create a folder named 
Camera and put all the xml review filesa about that camera inside the folder. After that you also need to add a aspect file 
inside this folder. </p>
<p>An example of aspect file(the name of the aspect file must have an "aspect" string in it):
<Camera><Screen>Screen|LED</Screen><Images>Image|Quality|Prints|Color|Clarity|Pictures|Photos|Snapshots|Indoor shots|Outdoor shots </Images><Battery>Batter</Battery><Zoom>Zoom|Digital zoom</Zoom><Features>Feature|Setting|Auto</Features><Price>Price</Price></Camera>
</p>
<li>  After that you can run the main class in Extractor.class and you will be able to find the results inside the .../xinghuangxu.lingpipe/Sentiment_DB/Log_DB folder.
</ol>
</li>
<br>
<hr>
<li>
Run in JVM:
<ol>
<li>Download the project from the repository, install java 7(JVM)
<li>open your terminal, navigate to the project directory ../xinghuangxu.lingpipe/
<li> input command: <code>java -jar kdd-lingpipe-sentiment.jar - l - r </code>
<li> If you want to run a new group of data.<br>
Put your data into the Review_DB folder which will be created under <strong>.../xinghuangxu.lingpipe/Sentiment_DB/</strong> the first time you run the program.
For example, if you want to get the sentiment of camera. You can create a folder named 
Camera and put all the xml review filesa about that camera inside the folder. After that you also need to add a aspect file 
inside this folder. 
<p>An example of aspect file(the name of the aspect file must have an "aspect" string in it):
<Camera><Screen>Screen|LED</Screen><Images>Image|Quality|Prints|Color|Clarity|Pictures|Photos|Snapshots|Indoor shots|Outdoor shots </Images><Battery>Batter</Battery><Zoom>Zoom|Digital zoom</Zoom><Features>Feature|Setting|Auto</Features><Price>Price</Price></Camera>
</p>
<li>input command:<code> java -jar kdd-lingpipe-sentiment.jar - l - r</code>
<li>After that you can run the extractor and you will be able to find the results inside the .../xinghuangxu.lingpipe/Sentiment_DB/Log_DB folder.
 </ol>
 </li>
</ul>