<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <style type="text/css">
        .fileDrop {
            width: 100px;
            height: 200px;
            border: 1px dotted blue;
        }

        small {
            margin-left: 3px;
            font-weight: bold;
            color: grey;
        }
    </style>
</head>
<body>

<h3>Ajax File Upload</h3>
<div class='fileDrop'></div>
<div class='uploadedList'></div>


</body>

<script type="text/javascript">
    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;

        var file = files[0];

        //console.log(file);
        var formData = new FormData();
        formData.append("file", file);

        $.ajax({
            url: '/uploadAjax',
            data: formData,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data) {

              alert(data);
            }
        });
    });
</script>
</html>
