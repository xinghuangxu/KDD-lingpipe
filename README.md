KDD-lingpipe
============

Use lingpipe to do sentiment analysis<br>

How to run and configure the sentiment analysis on aspect-level.<br>


<ul>
<li>Run on eclipse:
<ol>
<li> You need a plugin called "egit" to clone the repository from here to your local machine.
<li> After the cloning, import the project into your workspace
<li> You need to add junit-4.8.2.jar into your project java build path. Then Compile the project. 
<li>  Run the <strong>Extrator</strong> class under package <strong>xinghuangxu.lingpipe.snipeet.extractor</strong><br>
 Run configurations-> Program Arguments -> Input the Command: <code>- l + r</code>  <br>
l and r are the left and right size of the snipeet. 
For example: if [ - 5 + 5 ] is input, snipeet will look like 
[w1 w2 w3 w4 w5 target w1 w2 w3 w4 w5]
<li>  After that you can run the main class in Extractor.class and you will be able to find the results inside the <strong>.../xinghuangxu.lingpipe/Sentiment_DB/Log_DB</strong> folder.
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
<li>After that you can run the extractor and you will be able to find the results inside the <strong>.../xinghuangxu.lingpipe/Sentiment_DB/Log_DB</strong> folder.
 </ol>
 </li>
</ul>


<li>
Configuration:<br>
<ul>
<li><table>
<caption>Folder Structure:</caption>
<th colspan="3">Sentiment_DB</th>
<tr>
<td>Training_DB</td>
<td>Review_DB</td>
<td>Log_DB</td>
</tr>
</table> 
<li>If you want to run a new group of data.<br>
Put your data into the <strong>Review_DB</strong> folder which will be created under <strong>.../xinghuangxu.lingpipe/Sentiment_DB/</strong> the first time you run the program.
For example, if you want to get the sentiment of camera. You can create a folder named 
Camera and put all the xml review files inside that folder. After that you also need to add a aspect file 
inside this folder. 
<p>An example of aspect file(the name of the aspect file must include an "aspect" string):
<div>&lt;Camera>&lt;Screen>Screen|LED&lt;/Screen>&lt;Images>Image|Quality|Prints|Color|Clarity|Pictures|Photos|Snapshots|Indoor shots|Outdoor shots &lt;/Images>&lt;Battery>Batter&lt;/Battery>&lt;Zoom>Zoom|Digital zoom&lt;/Zoom>&lt;Features>Feature|Setting|Auto&lt;/Features>&lt;Price>Price&lt;/Price>&lt;/Camera>
</div>
</p>
</ul>
</li>


