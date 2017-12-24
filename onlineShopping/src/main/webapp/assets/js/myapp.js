$(function() {

	// Solving active menu tab in navbar
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for datatables

	var $table = $('#productListTable');

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products'
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products'
		}

		$table.DataTable({
			lengthMenu : [ [ 3, 4, 10, -1 ],
					[ '3 Records', '5 Records', '10 Records', 'All' ] ],
			pageLength : 5,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [ 
						{
							data : 'code',
							mRender : function(data, type, row) {
			            		return '<img width=100px; hieght=100px; src="'+window.contextRoot+'/resources/images/'+data+'.jpg"/>'
			            	}
						},
			            {
			            	data : 'name'
			            }, 
			            {
			            	data : 'brand'
			            }, 
			            {
			            	data : 'unitPrice',
			            	mRender : function(data, type, row) {
			            		return '&#8377 ' + data
			            	}
			            }, 
			            {
			            	data : 'quantity',
			            	mRender : function(data, type, row) {

								if (data < 1) {
									return '<span style="color:red">Out of Stock!</span>';
								}

								return data;

							}
			            }, 
			            {
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row) {
								var str = '';
								str += '<a href="' +window.contextRoot+ '/show/'+data+'/product" clss="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
								
								if (row.quantity < 1) {
									str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
								} else {
									str += '<a href="'+ window.contextRoot+ '/cart/add/'+ data+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
								}
								
								return str;
							}
			            }
			      ]
		});
	}

});