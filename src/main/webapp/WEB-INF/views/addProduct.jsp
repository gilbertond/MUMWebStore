<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Product</title>
    <link rel="stylesheet" href="css/addProduct.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<%--style="width: 55%; margin: auto; border: 3px solid rgb(0, 191, 255); padding: 20px"--%>
<div id="container">
    <form action="#" method="post">
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField">Catagory
            </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
                       name="catagory" placeholder="Catagory" type="text"/>
            </div>
        </div>

        <div class="form-group required">
            <label class="control-label col-md-4  requiredField"> Name </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
                       name="name" placeholder="Name" type="text"/>
            </div>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField"> Cost
            </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
                       name="cost" placeholder="Cost" type="text"/>
            </div>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField">Quantity
            </label>
            <div class="controls col-md-8 ">
                <input class="input-md  textinput textInput form-control"
                       name="quantity" placeholder="Quantity" type="text"/>
            </div>
        </div>
        <div class="form-group required">
            <label class="control-label col-md-4  requiredField">
                Product Description </label>
            <div class="controls col-md-8 ">
					<textarea class="input-md  textinput textInput form-control"
                              name="productdescription"></textarea>
            </div>
        </div>
        <div class="container">
            <div class="col-md-6">
                <div class="form-group">
                    <label>Upload Image</label>
                    <div class="form-group">
                    <input type="file" id="imgInp"
                    class="form-control btn btn-default"> <input
                    type="hidden" name="imageName" value="default.jpg">
                    <div id="upload" style="display: none;">Uploading..</div>
                    <div id="message"></div>
                    </div>
                </div>
            </div>
        </div>

        <br/>
        <div class="buttons">
            <button type="submit" class="btn btn-primary">Add Product</button>
        </div>

    </form>

</div>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript" src="javascript/fileupload.js"></script>

</body>
</html>