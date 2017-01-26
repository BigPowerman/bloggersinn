$(document)
		.ready(
				function() {
					var globalUserName;
					var globalBlogId;
					var registration = function() {
						$('#register')
				 				.click(
										function(event) {
											event.stopImmediatePropagation();
											var name = $('#name').val();
											$('#name').val("");
											var userName = $('#userIds').val();
											$('#userIds').val("");
											var pwd = $('#pwd').val();
											$('#pwd').val("");
											var cpwd = $('#cpwd').val();
											$('#cpwd').val("");
											var emailId = $('#emailIds').val();
											$('#emailIds').val("");
											var interest = $('#interests')
													.val();
											$('#interests')
													.val("");
											var secQuestion = $('#secQuestion')
													.val();
											$('#secQuestion')
													.val("");
											var secAnswer = $('#secAnswer')
													.val();
											$('#secAnswer')
													.val("");

											var Users = {
												name : name,
												userName : userName,
												password : pwd,
												emailId : emailId,
												securityQuestion : secQuestion,
												securityAnswer : secAnswer,
												interests : interest
											};
											$.ajax({
														url : 'http://localhost:8080/BloggersInn/blog/user/add',
														type : 'post',
														contentType : 'application/json',
														success : function(
																response) {
															$("#resultAdd")
																	.html(
																			"Hi : "
																					+ response
																					+ " you are succesfully registered");
															$(".register")
																	.hide();
															$(".regResult")
																	.show();
														},
														error : function(
																reponse) {
															$("#resultAdd")
																	.html(
																			"Sorry, Registration Failed!!!");
															$(".register")
																	.hide();
															$(".regResult")
																	.show();

														},
														data : JSON
																.stringify(Users)
													});
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
					});
					$('#loginButton')
							.click(
									function(event) {
										event.stopImmediatePropagation();
										var userName = $('#userName').val();
										$('#userName').val("");
										var password = $('#password').val();
										$('#password').val("");
										var Users = {
											userName : userName,
											password : password
										};
										$
												.ajax({
													url : 'http://localhost:8080/BloggersInn/blog/user/login',
													type : 'post',
													contentType : 'application/json',
													success : function(response){
														globalUserName = response.userName;
														$('#user').html("Hi " + globalUserName);
														$('#userDiv').show();
														$('.login').hide();
														$('.advertisement').hide();
														$('.centerContentHolder').show();
														$('.leftContentHolder').show();
														$('.rightContentHolder').show();
														$('.regResult').hide();
														$('#signIn').hide();
														$('#myProfile').show();
														$('#home').show();
														$('#myBlogs').show();
														$('#logout').show();
														$('#viewCreatedBlogSection').hide();
														$('#viewSearchedBlogSection').hide();
														$('#listBlogSection').hide();
													},
													error : function(response){
														$('#alertError').show();
														$("#errorReport").html(" "+response.responseText);
														$("#errorReport").show();
													},
													data : JSON
															.stringify(Users)
												});
									});
					$('#blogSubmit').click(function(){
						var heading = $('#blogHeading').val();
						$('#blogHeading').val("");
						var content = $('#blogContent').val();
						$('#blogContent').val("");
						var userName = globalUserName;
						
						var blog = {
								heading: heading,
								content: content,
								userName: userName
						};
						$
						.ajax({
							url : 'http://localhost:8080/BloggersInn/blog/blog/add',
							type : 'post',
							contentType : 'application/json',
							success : function(response){
								globalBlogId = response.id;
								$('#createBlogSection').hide();
								$('#searchBlogContent').hide();
								$('#listBlogSection').hide();
								$('#viewCreatedBlogSection').show();
								$('#cBlogHeading').html(response.heading);
								$('#cBlogContent').html(response.content);
								$('#cBlogDate').html(response.postedDate);
							},
							error : function(response){
								alert("Error while creating a blog ")
							},
							data : JSON
									.stringify(blog)
						});
						
						
					});
					$('#myBlogs').click(function(){
						var userName = globalUserName;
						var myblog = [];
						
						$
						.ajax({
							url : 'http://localhost:8080/BloggersInn/blog/user/getUserByName/' + userName,
							type : 'get',
							contentType : 'application/json',
							success : function(response){
								myblog = response.myBlogs;
						    	$('#viewCreatedBlogSection').hide();
								$('#createBlogSection').hide();
								$('#listBlogSection').show();
								console.log(myblog);
								$.map(myblog, function(val, index) {
									var container = document.getElementById('listBlogSection');
									var numChild = $('#listBlogSection').children().length+1;
									var blogListDiv = function(){
										var panelDefault = document.createElement("div");
										panelDefault.setAttribute('class','panel panel-default');
										var panelHeading = document.createElement('div');
										panelHeading.setAttribute('class','panel-heading');
										panelDefault.appendChild(panelHeading);
										var cheading = document.createElement('h2');
										cheading.setAttribute('class','panel-title');
										cheading.setAttribute('id','lBlogHeading'+numChild);
										panelHeading.appendChild(cheading);
										var panelBody = document.createElement('div');
										panelBody.setAttribute('class','panel-body');
										panelBody.setAttribute('id','listBlogContent');							
										var cdate = document.createElement('p');
										cdate.setAttribute('id','lBlogDate'+numChild);
										panelBody.appendChild(cdate);
										var ccontent = document.createElement('div');
										ccontent.setAttribute('id','lBlogContent'+numChild);
										panelBody.appendChild(ccontent);
										panelDefault.appendChild(panelBody);
										return panelDefault;
									}
									container.appendChild(blogListDiv());
									$('#lBlogHeading'+numChild).html(val.heading);
									$('#lBlogContent'+numChild).html(val.content);
									$('#lBlogDate'+numChild).html("Posted on " +val.postedDate);
									});
							},
							error : function(response){
								alert("Error while listing blog ");
							}
						});
	
						
					});
					$('#postComment').click(function(){
						var content = $('#comment').val();
						$('#comment').val("");
						var userName = globalUserName;
						var blog = globalBlogId;
						var comment = {
							content : content,
							userName : userName,
							blog : blog
						};
						$.ajax({
							url : 'http://localhost:8080/BloggersInn/blog/comment/add',
							type : 'post',
							contentType : 'application/json',
							success : function(response){
								var numChild = $('#commentContent').children().length+1;
								var commentDiv = function(){
									var panelDefault = document.createElement("div");
									panelDefault.setAttribute('class','panel panel-default');
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
									return panelDefault;
								}
								$('#commentContent').append(commentDiv()); 
								$('#pUserName'+numChild).html(response.userName);
								$('#pComment'+numChild).html(response.content);
								$('#pDate'+numChild).html("Posted on "+response.postedDate);
								$('#comment').val("");
							},
							error : function(response){
								alert("Error while creating a blog ")
							},
							data : JSON
									.stringify(comment)
						});

					});
					
					$('#createBlogLink').click(function(){
						document.getElementById('createBlogLink').setAttribute('class','list-group-item active');
						document.getElementById('editBlogLink').setAttribute('class','list-group-item');
						document.getElementById('discover').setAttribute('class','list-group-item');
						$('#createBlogSection').show();
						$('#viewCreatedBlogSection').hide();
						$('#viewSearchedBlogSection').hide();
					});
					$('#editBlogLink').click(function(){
						document.getElementById('createBlogLink').setAttribute('class','list-group-item');
						document.getElementById('editBlogLink').setAttribute('class','list-group-item active');
						document.getElementById('discover').setAttribute('class','list-group-item');
					});
					$('#discover').click(function(){
						document.getElementById('createBlogLink').setAttribute('class','list-group-item');
						document.getElementById('editBlogLink').setAttribute('class','list-group-item');
						document.getElementById('discover').setAttribute('class','list-group-item active');
						var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getAllBlogs/';
							$
						.ajax({
							url : $searchUrl,
							type : 'get',
							contentType : 'application/json',
							success : function(response){
								// globalBlogId = response.id;
								$('#createBlogSection').hide();
								$('#viewCreatedBlogSection').hide();
								$('#viewSearchedBlogSection').show();
								$('#viewSearchedBlogSection').empty();
								// $('#sBlogHeading').html(response.heading);
								// $('#sBlogContent').html(response.content);
								// $('#sBlogDate').html(response.postedDate);
								// $('.blogHeadingLink').click(function(){
								// 	$('#searchBlogContent').slideToggle();
								// });
								var blog = response;
								listBlogFunction(blog);
							},
							error : function(response){
								alert("Error while searching blog ")
							},
							
						});
					}); 
				 	
					$('#logout').click(function(){
						$('.login').show();
						$('.advertisement').show();
						$('.centerContentHolder').hide();
						$('.leftContentHolder').hide();
						$('.rightContentHolder').hide();
						$('.regResult').hide();
						$('#signIn').show();
						$('#myProfile').hide();
						$('#home').hide();
						$('#myBlogs').hide();
						$('#logout').hide();
						$('#user').hide();
						globalUserName = undefined;
						globalBlogId = undefined;
					});
					
					var listBlogFunction = function(blogArray){
						for(var i=0;i<blogArray.length;i++){
							var panelDefault = document.createElement("div");
							panelDefault.setAttribute('class','panel panel-default');
							var panelHeading = document.createElement('div');
							panelHeading.setAttribute('class','panel-heading blogHeadingLink'+i);
							panelDefault.appendChild(panelHeading);
							var heading = document.createElement('p');
							heading.setAttribute('id','sBlogHeading'+i);
							panelHeading.appendChild(heading);
							var panelBody = document.createElement('div');
							panelBody.setAttribute('class','panel-body');
							panelDefault.appendChild(panelBody);
							var sdate = document.createElement('p');
							sdate.setAttribute('id','sBlogDate'+i);
							panelBody.appendChild(sdate);
							var cnt = document.createElement('p');
							cnt.setAttribute('id','sBlogContent'+i);
							panelBody.appendChild(cnt);
							heading.innerHTML=blogArray[i].heading;
							sdate.innerHTML=blogArray[i].postedDate;
							cnt.innerHTML=blogArray[i].content;
							$('#viewSearchedBlogSection').append(panelDefault);
						}

					};

					$('#searchBlogButton').click(function(){

						var $searchBlog = $('#searchBlogInput').val();
						$('#searchBlogInput').val("");
						var $searchUrl = 'http://localhost:8080/BloggersInn/blog/blog/getBlogByHeading/' + $searchBlog;
							$
						.ajax({
							url : $searchUrl,
							type : 'get',
							contentType : 'application/json',
							success : function(response){
								// globalBlogId = response.id;
								$('#createBlogSection').hide();
								$('#viewCreatedBlogSection').hide();
								$('#viewSearchedBlogSection').show();
								$('#viewSearchedBlogSection').empty();
								// $('#sBlogHeading').html(response.heading);
								// $('#sBlogContent').html(response.content);
								// $('#sBlogDate').html(response.postedDate);
								// $('.blogHeadingLink').click(function(){
								// 	$('#searchBlogContent').slideToggle();
								// });
								var blog = response;
								listBlogFunction(blog);
							},
							error : function(response){
								alert("Error while searching blog ")
							},
							
						});
						
					});
						
					
					
					
				});