$(document).ready(function() {
	loadData();
	$("#btn2").click(function() {
		$("#updateForm").css("display", "none");
	});
	$("#qty").keyup(function() {
		calculateTotal();
	});
	$("#btn1").click(function() {
		updateOrder();

	});
});

function updateOrder() {
	var id = $("#orderid").val();
	var order_qty = parseInt($("#qty").val());
	var email = $("#email").val();
	var did = $("#dishid").val();
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
			dishID: did,
			qty: order_qty,
			custEmail: email
		}
		var requestJSON = JSON.stringify(ordermodel);
		$.ajax({
			type: "PUT",
			url: "http://localhost:8091/orders/" + id,
			headers: {
				"Content-Type": "application/json"
			},
			data: requestJSON,
			success: function(data) {
				alert("Update Order Successfull");
				$("#updateForm").css("display", "none");
				loadData();
			},
			error: function(data) {
				alert("Update Order not Successfull");
				$("#updateForm").css("display", "none");
			}
		});
	} else {
		alert(message);
	}
}
function calculateTotal() {
	var price = parseFloat($("#dishprice").text());
	var qty = parseInt($("#qty").val());
	if (isNaN(qty)) {
		qty = 0;
	}
	var total = qty * price;
	$("#total").text(total);

}

function loadData() {
	var tableBody = $("#table_body");
	fetch('http://localhost:8091/orders') // fetch data from our service
		.then(orders => orders.json()) // JSONify the data returned
		.then(function(orders) {
			$(tableBody).empty();
			if (orders.length == 0) { // If there is no data present
				// Prepare a row for display no data
				const tr = $("<tr></tr>")
					.append('<td colspan="7" align="center">No Order Details</td>');
				// Add table row in table body
				tr.appendTo(tableBody);
			} else {
				$.each(orders, function(key, item) {
					fetch('http://localhost:8091/dishes/' + item.dishID) // fetch data from our service
						.then(dish => dish.json()) // JSONify the data returned
						.then(function(dish) { // with the JSON data
							var total = item.qty * dish.dishPrice;
							const tr = $("<tr></tr>")
								.append($("<td></td>").text(item.orderID))
								.append($("<td></td>").text(dish.dishName))
								.append($("<td></td>").text(dish.dishPrice))
								.append($("<td></td>").text(item.qty))
								.append($("<td></td>").text(total))
								.append($("<td></td>").append('<a href="#">Edit This Order</a>')
									.on("click", function() {
										editOrder(item.orderID, item.dishID, dish.dishName, dish.dishPrice, item.qty, total, item.custEmail);
									})
								)
								.append($("<td></td>").append('<a href="#">Delete This Order</a>')
									.on("click", function() {
										removeOrder(item.orderID);
									})
								);
							tr.appendTo(tableBody);
						});
				});
			}
		});
}

function editOrder(orderid, dishid, dishname, price, qty, total, email) {
	$("#updateForm").css("display", "block");
	$("#oid").html(orderid);
	$("#dishname").html(dishname);
	$("#total").html(total);
	$("#dishprice").html(price);
	$("#orderid").val(orderid);
	$("#dishid").val(orderid);
	$("#email").val(email);
	$("#qty").val(qty);

}

function removeOrder(id) {
	let decision = confirm("You Will Not Undo... If Details are Deleted. Are You Sure?");

	if (decision) {
		// Request Web API to Delete Order
		$.ajax({
			type: "DELETE",
			url: 'http://localhost:8091/orders' + "/" + id,
		}).done(function(response) {
			// Refresh Order Details
			loadData();
		});
	}

}