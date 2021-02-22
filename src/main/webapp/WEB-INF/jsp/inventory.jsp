<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>IKEA Inventory!</title>
</head>

<body>

<h2 class="hello-title">IKEA Inventory!</h2>
<form name="fileUpload" method="POST" action="uploadInventoryFile" enctype="multipart/form-data">
    <label>Select Inventory File</label> <br />
    <input type="file" name="file" />
    <input type="submit" name="submit" value="Upload" />
</form>

<br><br>

<form name="fileUpload" method="POST" action="uploadProductFile" enctype="multipart/form-data">
    <label>Select Product File</label> <br />
    <input type="file" name="file" />
    <input type="submit" name="submit" value="Upload" />
</form>

</body>

</html>