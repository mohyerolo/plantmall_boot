<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<head>
	<title>식몰</title>
	<style>
	  table {
	    width: 100%;
	    border-collapse: collapse;
	  }
	  td {
	    border-bottom: 1px solid #444444;
	    padding: 10px;
	  }
	</style>
</head>
<body>
<h2>제품 수정</h2>
<div>
<form id="editForm" method="post">
 	<table>
		<tr>
			<td rowspan="6" style="border-bottom: none; border:1px solid;">
				<img src="temp.jpg" style="width:200px">	
				<input type="file" id="image" name="image">							
			</td>
			<td>제품명</td>
			<td><input type="text" id="name" name="name"></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input type="text" id="price" name="price">원</td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td>
				<select id="category" name="category">
					<option value="꽃">꽃</option>
					<option value="야생화">야생화</option>
					<option value="허브">허브</option>
					<option value="나무">나무</option>
					<option value="다육">다육</option>
					<option value="해조">해조</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>수량</td>
			<td><input type="text" id="quantity" name="quantity">개</td>
		</tr>
		<tr>
			<td style="border-bottom: none">제품 설명</td>
			<td style="border-bottom: none">
				<textarea cols="50" rows="10" placeholder="제품의 상세 설명을 작성해주세요" id="description" name="description"></textarea>
			</td>
		
		</tr>
		<tr>
			<td style="border-bottom: none"></td>
			<td style="border-bottom: none"></td>
			<td style="border-bottom: none">
				<input type="button" value="수정" onclick="alert('해당 제품을 수정 하시겠습니까?')" id="editBtn">
				<input type="button" value="삭제" onclick="alert('해당 제품을 삭제 하시겠습니까?')" id="deleteBtn">
			</td>
		</tr>
	</table>
	</form>
</div>
<script>
	$(document).ready(function(){
		$("#editBtn").click(function(){
			var productName = $("#name").val();
			var productPrice = $("#price").val();
			var productDesc = $("#description").val();
			var productPhoto = $("#image").val();
			
			if(productName == "") {
				alert("상품명을 입력해주세요");
				productName.foucs();
			} else if (productPrice == "") {
				alert("상품 가격을 입력해주세요");
				productPrice.focus();
			} else if (productDesc == "") {
				alert("상품 설명을 입력해주세요");
				productDesc.focus();
			} /* else if (productPhoto == "") {
				alert("상품 사진을 입력해주세요");
				productPhoto.focus();
			} */
			document.editForm.action = "${path}/shop/product/update.do";
			document.editForm.submit();
		});
		$("#deleteBtn").click(function(){
			if(confirm("상품을 삭제하시겠습니까?")){
				document.editForm.action = "${path}/shop/product/delete.do";
				document.editForm.submit();
			}
		});
	});
</script>
</body>
</html>
        