<div class="container">

	<div class="row">

		<!-- for sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- for actual products -->
		<div class="col-md-9">

			<!-- Adding breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">

					<c:if test="${userClickAllProducts == true}">
						<script>
							window.categoryId = '';
						</script>

						<div class="breadcrumb">
							<li><a href="${contextRoot}/home">Home / </a></li>
							<li class="active">All Products</li>
						</div>

					</c:if>

					<c:if test="${userClickCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>

						<div class="breadcrumb">
							<li><a href="${contextRoot}/home">Home / </a></li>
							<li class="active">Category /</li>
							<li class="active">${category.name}</li>
						</div>

					</c:if>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">

					<table id="productListTable"
						class="table table-striped table-borderd">

						<thead>
							<tr>
								<th>Image</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty Available</th>
								<th></th>
							</tr>
						</thead>

						<tfoot>
							<tr>
								<th>Image</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty Available</th>
								<th></th>
							</tr>
						</tfoot>

					</table>

				</div>
			</div>

		</div>
	</div>

</div>