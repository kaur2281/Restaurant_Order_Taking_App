function calculateTotal() {
	var id = $("#dish").val();
	var qty = parseInt($("#qty").val());
	if (isNaN(qty)) {
		qty = 0;
	}
	fetch('http://localhost:8091/dishes/' + id) // fetch data from our service
		.then(data => data.json()) // JSONify the data returned
		.then(function(data) { // with the JSON data
			console.log(data);
			var total = qty * data.dishPrice;
			$("#total").text("$ " + total);
		});
}

$(document).ready(function() {
	$("#dish").change(function() {
		calculateTotal();
	});

	$("#qty").keyup(function() {
		calculateTotal();
	});
	$("#btn").click(function() {
		var id = $("#dish").val();
		var order_qty = parseInt($("#qty").val());
		var email = $("#email").val();
		var message = "";
		var valid = true;
		if (email.length == 0) {
			message = "Please Enter Some Value in Email Box<br>";
			valid = false;
		}
		if (isNaN(order_qty)) {
			message = "Not a Valid Quantity<br>";
			valid = false;
		}
		else if (order_qty <= 0) {
			message = "Quantity must be a positive Number<br>"
			valid = false;
		}
		if (valid) {
			var ordermodel = {
				dishID: id,
				qty: order_qty,
				custEmail: email
			}
			var requestJSON = JSON.stringify(ordermodel);
			$.ajax({
				type: "POST",
				url: "http://localhost:8091/orders",
				headers: {
					"Content-Type": "application/json"
				},
				data: requestJSON,
				success: function(data) {
					$("#message").html("Your Order is Placed. Order ID is " + data);			
				},
				error: function(data) {
					$("#message").html("Your Order is not Placed");
				}
			});
		}else{
		$("#message").html(message);	
		}
		
	});
});
