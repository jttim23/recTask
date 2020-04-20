# Read Me First
This is app build for recruitment 
<p>It runs console to ask for input JSON and/or CSV 
File and calculate salaries for each job.
<p>If job or salary field contains errors, whole JSON 
Object or CSV row will not be processed.
<p>If salary is saved with ',' as delimiter, delimiter will be converted to '.' 
to unify decimal format.
<p>Job letter case is irrelevant 
eg. ('Teacher' and 'teacher' will be processed as the same job) </p> 


# Setup
<p>1. Please run git command in your destination folder:

##### git clone https://github.com/jttim23/recTask.git
<p>2. Change direction:

#####cd recTask
<p>3. Run maven command to create executable jar file.

#####mvn package
<p>4. Run fat-jar using command:

#####java -jar target/recTask.jar