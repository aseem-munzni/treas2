1. Code Repository URL : 
https://github.com/aseem-munzni/treas2.git


2. Application hosted at URL :
http://secondtreasures-env-1.bdfbhww69w.us-east-2.elasticbeanstalk.com/



3. build.gradle file shows a pipeline for deployment
	-- clone repository files
	-- compile java source files to create compiled class files
	-- create a ".war" file for deployment on Tomcat servlet engine,
		with appropriate web-app structure
	-- copy ".war" file into the appropriate directory on Tomcat server,
		on AWS elastic beanstalk


4. "test" folder contains some tests for server and client side code
	-- test servlet function for correct business logic
	-- validate form inputs for format, non-null, correct-datatype, etc.
	-- Function tests would involve end-to-end testing. 
		Mock-up entities would be used on server side
		Selenium gesture/key sequences would be used for automation on client side


5. Employee ID field value 111111, or 555555, or 999999,
	changes displayed ISBN to an active HREF link.
	This link provides enhanced access:
	-- to book details page for additional book info
	-- to update book info, or to delete a book, etc.


6. There is no way to add a book at present. 
	So be careful while deleting books from the list.
	Once all the books are deleted, the BookServer servlet will need to be restarted 
	for use again. I will need to restart it from AWS console.
	Book info can be updated. Search is operational.


7. Multiple approaches have been demonstrated on the front-end,
	using javascript, AJAX, pure HTML, CSS, etc.
	Security is not robust but works.
	There are no multi-page, long running, transactions.
	So Session management is not implemented.
	Application uses simple REST calls.


8. Application is neither complete nor robust. But basics work. Give it a try.

