<%--
  Created by IntelliJ IDEA.
  User: Niroj
  Date: 8/12/2017
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>MUM Signup Form</title>
        <link rel="stylesheet" href="../css/signup.css">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">

        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
              crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>


    </head>
    <body>
        <div id="container">

            <h1>Sign up now</h1>
            <form id="signup-form" method="post">
                <div class="form-group">
                    <label>First Name</label> <input type="text" name="firstname"
                                                     class="form-control" placeholder="first name" required>
                </div>

                <div class="form-group">
                    <label>Last Name</label> <input type="text" name="lastname"
                                                    class="form-control" placeholder="last name" required>
                </div>

                <div class="form-group">
                    <label>Address</label> <input type="text" name="address"
                                                  class="form-control" placeholder="Address" required>
                </div>

                <div class="form-group">
                    <label>Email address</label> <input type="email" name="email" id="emailId"
                                                        class="form-control" placeholder="Email address" required>
                    <div id="emailmsg"></div>
                </div>

                <div class="form-group">
                    <label>Password</label> <input type="password" name="password"
                                                   id="pwd" class="form-control" placeholder="Password"
                                                   pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" required>
                </div>

                <div class="panel panel-default">

                    <div class="panel-body">

                        <fieldset class="col-md-6">
                            <legend>Address</legend>

                            <div class="form-group">
                                <label>Street</label> <input type="text" name="street"
                                                             class="form-control" placeholder="street" required>
                            </div>
                            <div class="form-group">
                                <label>City</label> <input type="text" name="city"
                                                           class="form-control" placeholder="city" required>
                            </div>
                            <div class="form-group">
                                <label>State</label> <input type="text" name="state"
                                                            class="form-control" placeholder="state" required>
                            </div>
                            <div class="form-group">
                                <label>Zip</label> <input type="text" name="zip"
                                                          class="form-control" placeholder="zip" required>
                            </div>
                            <div class="form-group">
                                <label>Country</label> <input type="text" name="country"
                                                              class="form-control" placeholder="country" required>
                            </div>
                        </fieldset>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <button type="submit" name="signup-btn" class="btn btn-primary">Sign
                    me up
                </button>
            </form>
        </div>
    </body>
</html>