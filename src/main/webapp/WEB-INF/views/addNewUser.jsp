<%--
  Created by IntelliJ IDEA.
  User: Niroj
  Date: 8/12/2017
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>

<div id="container">
    <h1>Register new user</h1>
    <div id="container">
        <form id="signup-form" id="saveForm" method="post">
            <div class="form-group">
                <label>First Name</label> 
                <input type="text" name="firstname" class="form-control" placeholder="first name" required/>
            </div>
            <div class="form-group">
                <label>Last Name</label> 
                <input type="text" name="lastname" class="form-control" placeholder="last name" required/>
            </div>
            <div class="form-group">
                <label>Phone</label> 
                <input type="tel" name="phone" class="form-control" placeholder="phone" required/>
            </div>                
            <div class="form-group">
                <label>Email address</label> 
                <input type="email" name="email" id="emailId" class="form-control" placeholder="Email address" required/>
                <div id="emailmsg"></div>
            </div>
            <div style="width: 80%" class="form-group">
                <label class="col-2 col-form-label">Password</label> 
                <input type="password" name="password" id="pwd" class="form-control " placeholder="Password" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" required/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input onclick="callAjaxRequest('userSave', 'contectDiv', 'POST', $('#saveForm').serialize());" type="submit" value="Save user" name="Register" class="btn btn-primary"/>
        </form>
    </div>
</div>