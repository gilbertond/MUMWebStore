/**
 * 
 */

$(function () {
    $(".productdescription").click(
            function () {
                var th = $(this);
                $.post("/project_MUM-WebStore/ProductDescription", {
                    "id": th.attr("id")
                }).done(
                        function (product) {
                            product = $.parseJSON(product);
                            // var p = $("<p>", {
                            // "text" : product.productName
                            // });
                            var image = $("<img>", {
                                "class": "image",
                                "src": "images/" + product.imagePath,
                                "alt": "ladiesslipper"
                            });
                            var p1 = $("<p>", {
                                "text": product.productDescription
                            });
                            var p2 = $("<p>", {
                                "text": "Price: $" + product.unitPrice
                            });
                            var p3 = $("<p>", {
                                "text": "Quantity:" + product.quantity
                            });
                            var input = $('<input>', {
                                "type": 'hidden',
                                "name": 'productId',
                                "value": product.productId
                            });

                            var button = $('<button>', {
                                "type": "submit",
                                "text": "Add to Cart",
                                "class": "btn btn-primary"
                            });

                            var f = $("<form>", {
                                "action": $("#form-submit").val(),
                                "method": "post"
                            });
                            var btn = $('<button>', {
                                "type": "button",
                                "class": "btn btn-secondary",
                                "data-dismiss": "modal",
                                "text": "Close"
                            });

                            f.append(input).append(button).append(btn);

                            var div = $("<div>").append(image).append(p1)
                                    .append(p2).append(p3);
                            $(".modal .modal-body").html(div);
                            $(".modal .modal-footer").html(f);
                            $(".modal .modal-title").html(product.productName);
                            $("#modalId").modal('show');
                        });
            });
});