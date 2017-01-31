$(document)
		.ready(function(){
			var globalUserName = localStorage.getItem('user');
			var globalBlogId;
			var globalUserId;
			var sessionString;
			


			var registration = function() {
				var clearRegisterFields = function(){
					$('#name').val("");
					$('#userIds').val("");
					$('#pwd').val("");
					$('#cpwd').val("");
					$('#emailIds').val("");
					$('#interests').val("");
					$('#secQuestion').val("");
					$('#secAnswer').val("");
				};
				
				$('#register')
		 				.click(
								function(event) {
									event.stopImmediatePropagation();
									var name = $('#name').val();
									var userName = $('#userIds').val();
									var pwd = $('#pwd').val();
									var cpwd = $('#cpwd').val();
									var emailId = $('#emailIds').val();
									var interest = $('#interests')
											.val();
									var secQuestion = $('#secQuestion')
											.val();
									var secAnswer = $('#secAnswer')
											.val();
									
									var Users = {
										name : name,
										userName : userName,
										password : pwd,
										emailId : emailId,
										securityQuestion : secQuestion,
										securityAnswer : secAnswer,
										interests : interest
									};
									if(($('#name').val() == "") || ($('#userIds').val() == "") || ($('#pwd').val() == "") || ($('#cpwd').val() == "") || ($('#emailIds').val() == "") || ($('#interests').val() == "select") || ($('#interests').val() == "") || ($('#secQuestion').val() == "select") || ($('#secQuestion').val() == "") || ($('#secAnswer').val() == "")  ){
										$("#resultAdd").html("All the fields are mandatory.");
										$(".register").show();
										$(".regResult").show();
									}else if(($('#pwd').val()) != ($('#cpwd').val())){
										$("#resultAdd").html("Passwords not matching.");
										$(".register").show();
										$(".regResult").show();
									}else{
										$('#prog').progressbar({ value: 0 });
										
										$.ajax({
											url : 'http://localhost:8080/BloggersInn/blog/user/add',
											type : 'post',
											contentType : 'application/json',
											success : function(response) {
												$("#regAdd")
														.html("Hi : "+ response+ " you are succesfully registered");
												$(".successRegister").show();
												$(".register").hide();
												$(".login").show();
												$(".regResult").hide();
												$('#alertError').hide();
												clearRegisterFields();
											},
											error : function(response) {
												$("#resultAdd").html(" "+response.responseText);
												$(".register").show();
												$(".regResult").show();

											},
											data : JSON.stringify(Users),

										});
									}


								});



			};
			$('#regLink').click(function() {
				$('.login').hide();
				$('.register').show();
				registration();
			});
			$('#regButton').click(function() {
				$('.login').hide();
				$('.register').show();
				registration();
			});
			$('#signIn').click(function() {
				$('.register').hide();
				$('.login').show();
				$('.regResult').hide();
				$(".successRegister").hide();
				$('#alertError').hide();
			});
			
			$('#sendChat').click(function() { 
				var senderName = localStorage.getItem('user');
				
				var receiverName = $('#receiverName').val();
				$('#receiverName').val("");
				
				var text = $('#usermsg').val();
				$('#usermsg').val("");
				
				var Chats = {
						senderUserName : senderName,
						receiverUserName: receiverName,
						message : text
					};	
				
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/BloggersInn/blog/chat/add/"+sessionString,

					contentType : 'application/json',
					success: function(response){
						console.log(response);
					},
					error: function(response){
						console.log('Error while renderring chat');

					},
					data : JSON
							.stringify(Chats)
				});
			});
			

			var clearLoginFields = function(){
				$('#userName').val("");
				$('#password').val("");
			};

			$('#loginButton')
					.click(
							function(event) {
								event.stopImmediatePropagation();
								var userName = $('#userName').val();
								var password = $('#password').val();
								
								var Users = {
									userName : userName,
									password : password
								};

								if(userName == "" || password == ""){
									$('#alertError').show();
									$("#errorReport").html(" username and password cannot be empty");
									$("#errorReport").show();
									$('.successRegister').hide();
									$(".regResult").hide();
								}else{
									$.ajax({
									url : 'http://localhost:8080/BloggersInn/blog/user/login',
									type : 'post',
									contentType : 'application/json',
									success : function(response){
										globalUserName = response.userName;
										localStorage.setItem('user',response.userName);
										localStorage.setItem('sessionId',response.sessionId);
										console.log(localStorage.getItem('user'));
										console.log(localStorage.getItem('sessionId'));
										sessionString = '?sessionId='+ localStorage.getItem('sessionId')+'&user='+localStorage.getItem('user');
										$('#user').html("Hi " + localStorage.getItem('user') +" Welcome");
										$('#userDiv').show();
										$('.login').hide();
										$('.advertisement').hide();
										$('.centerContentHolder').show();
										$('.leftContentHolder').show();
										$('.rightContentHolder').show();
										$('.regResult').hide();
										$('#signIn').hide();
										$('#myProfile').show();
										$('#myBlogs').show();
										$('#logout').show();
										$('#viewCreatedBlogSection').hide();
										var mychat = response.myChat;
										console.log(mychat);
										$('#msgList').empty();
										if(mychat != null){
											for(var i=0;i<mychat.length;i++){
												$('#msgList').append("<font color=\"blue\">" +mychat[i].senderUserName + "@: </font>" + mychat[i].message + "<br>");
											}
										}
										
										clearLoginFields();
										var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getAllBlogs/'+sessionString;
										getAllBlog($searchUrl);
									},
									error : function(response){
										clearLoginFields();
										$('#alertError').show();
										$("#errorReport").html(" "+response.responseText);
										$("#errorReport").show();
										$('.successRegister').hide();
										$(".regResult").hide();
									},
									data : JSON
											.stringify(Users)
								});

								}
							});

			$('#blogSubmit').click(function(){
				var heading = $('#blogHeading').val();
				$('#blogHeading').val("");
				var content = $('#blogContent').val();
				$('#blogContent').val("");
				var userName = localStorage.getItem('user');
				
				var blog = {
						heading: heading,
						content: content,
						userName: userName
				};
				if(heading == "" || content == ""){

				} else {
				$
				.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/blog/add/'+sessionString,
					type : 'post',
					contentType : 'application/json',
					success : function(response){
						globalBlogId = response.id;
						$('#createBlogSection').hide();
						$('#searchBlogContent').hide();
						$('#viewCreatedBlogSection').show();
						$('#cBlogHeading').html(response.heading);
						$('#cBlogContent').html(response.content);
						$('#cBlogDate').html(response.postedDate);
						$('#viewClickedBlogSection').hide();
						$('#listBlogHeadings').hide();
						$('#commentContent').empty();
						document.getElementById('createBlogLink').setAttribute('class','list-group-item');
						document.getElementById('editBlogLink').setAttribute('class','list-group-item');
						document.getElementById('discover').setAttribute('class','list-group-item');

					},
					error : function(response){
						console.log("Error while creating a blog ");
					},
					data : JSON
							.stringify(blog)
				});
				}
				
			});
			
			$('#myBlogs').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item');
				var userName = localStorage.getItem('user');
				var myblog = [];
				
				$
				.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/user/getUserByName/' + userName+sessionString,
					type : 'get',
					contentType : 'application/json',
					success : function(response){
						myblog = response.myBlogs;
				    	$('#viewCreatedBlogSection').hide();
						$('#createBlogSection').hide();
						$('#userProfile').hide();
						$('#listBlogHeadings').show();
						$('#viewClickedBlogSection').hide();
						console.log(myblog);
						listAllHeadings(myblog);
					},
					error : function(response){
						console.log("Error while listing blog ");
					}
				});

				
			});
			
			var commentDiv = function(numChild, response){
				var panelDefault = document.createElement("div");
				panelDefault.setAttribute('class','panel panel-default ');
				var panelHeading = document.createElement('div');
				panelHeading.setAttribute('class','panel-heading');
				panelDefault.appendChild(panelHeading);
				var cuser = document.createElement('p');
				cuser.setAttribute('id','pUserName'+numChild);
				panelHeading.appendChild(cuser);
				var panelBody = document.createElement('div');
				panelBody.setAttribute('class','panel-body');
				panelDefault.appendChild(panelBody);
				var cdate = document.createElement('p');
				cdate.setAttribute('id','pDate'+numChild);
				panelBody.appendChild(cdate);
				var ccmt = document.createElement('p');
				ccmt.setAttribute('id','pComment'+numChild);
				panelBody.appendChild(ccmt);
				cuser.innerHTML = response.userName;
				ccmt.innerHTML = response.content;
				cdate.innerHTML = "Posted on "+response.postedDate;
				$('#comment').val("");
				return panelDefault;
			};
												
			
			$('#postComment').click(function(){
				var content = $('#comment').val();
				$('#comment').val("");
				var userName = localStorage.getItem('user');
				var blog = globalBlogId;
				var comment = {
					content : content,
					userName : userName,
					blog : blog
				};
				if(content == ""){
					
				} else {

				$.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/comment/add/'+sessionString,
					type : 'post',
					contentType : 'application/json',
					success : function(response){
						var numChild = $('#commentContent').children().length+1;
						$('#commentContent').append(commentDiv(numChild,response)); 
						
					},
					error : function(response){
						console.log("Error while creating a blog ");
					},
					data : JSON
							.stringify(comment)
				});
				}

			});
			
			$('#createBlogLink').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item active');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item');
				document.getElementById('updateFavorites').setAttribute('class','list-group-item');
				$('#createBlogSection').show();
				$('#viewCreatedBlogSection').hide();
				$('#userProfile').hide();
				$('#listBlogHeadings').hide();
				$('#viewClickedBlogSection').hide();
				
			});
			$('#editBlogLink').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item active');
				document.getElementById('discover').setAttribute('class','list-group-item');
			});
			
			var getAllBlog = function(searchUrl){
				$
				.ajax({
					url : searchUrl,
					type : 'get',
					contentType : 'application/json',
					success : function(response){
						$('#userProfile').hide();
						$('#createBlogSection').hide();
						$('#viewCreatedBlogSection').hide();
						$('#listBlogHeadings').show();
						$('#viewClickedBlogSection').hide();
						var blog = response;
						listAllHeadings(blog); 
					},
					error : function(response){
						console.log("Error while searching blog ")
					},
					
				});
			};
			$('#discover').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item active');
				document.getElementById('updateFavorites').setAttribute('class','list-group-item');
				var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getAllBlogs/';
				getAllBlog($searchUrl);	
			}); 
		 	
			$('#logout').click(function(){

				$
				.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/user/logout/'+globalUserName+sessionString,
					type : 'get',
					contentType : 'application/json',
					success : function(response){
						globalUserName = null;
						globalBlogId = null;
						globalUserId = null;
						$('.login').show();
						$('.advertisement').show();
						$('.centerContentHolder').hide();
						$('.leftContentHolder').hide();
						$('.rightContentHolder').hide();
						$('.regResult').hide();
						$('#signIn').show();
						$('#myProfile').hide();
						$('#myBlogs').hide();
						$('#logout').hide();
						$('#user').hide();
						$('#userProfile').hide();
						$(".successRegister").hide();
						localStorage.setItem('user',null);
						localStorage.setItem('sessionId',null);
					},
					error : function(response){
						console.log("Error while logout ");
					},
					
				});
			});

			$('#searchBlogButton').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item');
				var $searchBlog = $('#searchBlogInput').val();
				$('#searchBlogInput').val("");
				var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getBlogByHeading/' + $searchBlog+sessionString;
				getAllBlog($searchUrl);	
				
			});
				
			$('#myProfile').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item');
				$('#userProfile').show();
				$('#listBlogHeadings').hide();
				$('#viewClickedBlogSection').hide();
				$('#createBlogSection').hide();
				$('#viewCreatedBlogSection').hide();
				$
				.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/user/getUserByName/'+globalUserName+sessionString,
					type : 'get',
					contentType : 'application/json',
					success : function(response){
						globalUserId = response.id;
						$('#uName').val(response.name);
						$('#uUserName').val(response.userName);
						$('#uEmailIds').val(response.emailId);
						$('#uPwd').val(response.password);
						$('#uSecQuestion').val(response.securityQuestion);
						$('#uSecAnswer').val(response.securityAnswer);
						$('#uInterests').val(response.interests);
						$('#cCPwd').val(response.password);
						
						
					},
					error : function(response){
						console.log("Error while getting user ")
					}
				});
			});
			
			$('#update').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item');
				$('#userProfile').show();
				$('#createBlogSection').hide();
				$('#viewCreatedBlogSection').hide();
				var name = $('#uName').val();
				var userName = $('#uUserName').val();
				var id = globalUserId;
				var password = $('#uPwd').val();
				var secQuestion = $('#uSecQuestion').val();
				var secAnswer = $('#uSecAnswer').val();
				var interest = $('#uInterests').val();
				var emailId = $('#uEmailIds').val();
				var Users = {
						id : id,
						userName : userName,
						name : name,
						password : password,
						emailId : emailId,
						securityQuestion : secQuestion,
						securityAnswer : secAnswer,
						interests : interest
					};
				if(($('#name').val() == "") || ($('#userIds').val() == "") || ($('#pwd').val() == "") || ($('#cpwd').val() == "") || ($('#emailIds').val() == "") || ($('#interests').val() == "select") ||($('#interests').val() == "") || ($('#secQuestion').val() == "select") || ($('#secQuestion').val() == "")|| ($('#secAnswer').val() == "")  ){
					$('#updateUserResult').html("All Fields are mandatory");
				}else if(($('#pwd').val()) != ($('#cpwd').val())){
					$('#updateUserResult').html("passwords should match");
				}else{
					$.ajax({
						url : 'http://localhost:8080/BloggersInn/blog/user/update/'+sessionString,
						type : 'post',
						contentType : 'application/json',
						success : function(response){
							$('#updateUserResult').html("User Details updated successfully");
						
						},
						error : function(response){
							console.log("Error while updating user ")
						},
						data : JSON
						.stringify(Users)
					});
				}
				
			});
			
			var arrayBlog;
			var listAllHeadings = function(blog){
				$('#listBlogHeadings').empty();
				for(var i=0;i<blog.length;i++){
					var panelDefault = document.createElement("div");
					panelDefault.setAttribute('class','panel panel-primary');
					var panelBody = document.createElement('div');
					panelBody.setAttribute('class','panel-body');
					panelDefault.appendChild(panelBody);
					var aTag = document.createElement('a');
					aTag.setAttribute('href','#');
					aTag.setAttribute('id',i);
					aTag.innerHTML = blog[i].heading;
					panelBody.appendChild(aTag);
					$('#listBlogHeadings').append(panelDefault);
					 

				}
			};
			
			$('#zpostComment').click(function(){
				var content = $('#zcomment').val();
				$('#zcomment').val("");
				var userName = localStorage.getItem('user');
				var blog = globalBlogId;
				var comment = {
					content : content,
					userName : userName,
					blog : blog
				};
				if (contenet == ""){
					
				} else {
				$.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/comment/add/'+sessionString,
					type : 'post',
					contentType : 'application/json',
					success : function(response){
						var numChild = $('#zcommentContent').children().length+1;
						$('#zcommentContent').append(commentDiv(numChild,response)); 
						
					},
					error : function(response){
						console.log("Error while creating a blog ")
					},
					data : JSON
							.stringify(comment)
				});
				}

			});
			
			$('#listBlogHeadings').on('click','a',function(){
				var blogHeading = ($(this).text());
				console.log(blogHeading);
				$
				.ajax({
					url : 'http://localhost:8080/BloggersInn/blog/blog/getBlogByHeading/'+blogHeading+sessionString,
					type : 'get',
					contentType : 'application/json',
					success : function(response){
						var blogArray = response;
						
						globalBlogId = blogArray[0].id;
						$('#createBlogSection').hide();
						$('#searchBlogContent').hide();
						$('#viewCreatedBlogSection').hide();
						$('#viewClickedBlogSection').show();
						$('#listBlogHeadings').hide();
						$('#zBlogHeading').html(blogArray[0].heading);
						$('#zBlogContent').html(blogArray[0].content);
						$('#zBlogDate').html(blogArray[0].postedDate);
						document.getElementById('createBlogLink').setAttribute('class','list-group-item');
						document.getElementById('editBlogLink').setAttribute('class','list-group-item');
						document.getElementById('discover').setAttribute('class','list-group-item');
						$('#zcommentContent').empty();
						for(var j=0; j<blogArray[0].comments.length; j++){
							var numChild = $('#zcommentContent').children().length+1;
							$('#zcommentContent').append(commentDiv(numChild,blogArray[0].comments[j])); 
							
						}
					},
					error : function(response){
						console.log("Error while creating a blog ");
					}
				});
				

			});
			
			$('#updateFavorites').click(function(){
				document.getElementById('createBlogLink').setAttribute('class','list-group-item');
				document.getElementById('editBlogLink').setAttribute('class','list-group-item');
				document.getElementById('discover').setAttribute('class','list-group-item');
				document.getElementById('updateFavorites').setAttribute('class','list-group-item active');
				var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getAllBlogs/';
					$
				.ajax({
					url : $searchUrl,
					type : 'get',
					contentType : 'application/json',
					success : function(response){
						$('#userProfile').hide();
						$('#createBlogSection').hide();
						$('#viewCreatedBlogSection').hide();
						$('#viewSearchedBlogSection').hide();
						$('#listBlogHeadings').show();
						$('#listBlogSection').hide();
						var blog = response;
						listAllHeadings(blog);
						
					},
					error : function(response){
						console.log("Error while searching blog ")
					},
					
				});
			});
			
		
			
			
			if( globalUserName == "null" ){
				console.log('start from login page')
			}else{
				sessionString = '?sessionId='+ localStorage.getItem('sessionId')+'&user='+localStorage.getItem('user');
				$('#user').html("Hi " + localStorage.getItem('user') +" Welcome");
				$('#userDiv').show();
				$('.login').hide();
				$('.advertisement').hide();
				$('.centerContentHolder').show();
				$('.leftContentHolder').show();
				$('.rightContentHolder').show();
				$('.regResult').hide();
				$('#signIn').hide();
				$('#myProfile').show();
				$('#myBlogs').show();
				$('#logout').show();
				$('#viewCreatedBlogSection').hide();
				
				clearLoginFields();
				var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getAllBlogs/'+sessionString;
				getAllBlog($searchUrl);
			}
			
		}
				
				
				
				
				);