<%-- 
    Document   : passwordForm
    Created on : Aug 15, 2017, 3:44:30 PM
    Author     : gilberto
--%>

<style>
    #container {
        width: 80%;
    }
</style>
<div align="left" id="container">
    <div align="center" class="row" style="width: 100%; margin-right: 40%">
        <div align="left" style="width: 80%" class="">
            <h1>Register new user</h1>
            <form id="saveForm">                            
                <div style="width: 60%" class="form-group">
                    <label>Email address</label> 
                    <input type="email" name="email" id="emailId" class="form-control" placeholder="Email address" required/>
                    <div id="emailmsg"></div>
                </div>
                <div style="width: 60%" class="form-group">
                    <label class="col-2 col-form-label">Password</label> 
                    <input type="password" name="password" id="pwd" class="form-control " placeholder="Password" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" required/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button style="float: left" id="submitButton" type="button" class="btn btn-primary" 
                                    onclick="callAjaxRequest('updateAccount', 'contectDiv', 'POST', $('#saveForm').serialize());">Update credentials</button>
                
                <br/>
                ${message}
            </form>
        </div>
    </div>
</div>
