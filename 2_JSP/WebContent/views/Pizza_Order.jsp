<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String pizza = (String) request.getAttribute("pizza");
String[] topping = (String[]) request.getAttribute("topping");
String[] side = (String[]) request.getAttribute("side");
String toppings;
String sides;
//--null일때 출력문--
if(topping != null){ 
	toppings = String.join(",",topping);
}else{
	toppings = "선택하지 않으셨고";
}
if(side != null){
	sides = String.join(",",side) +"을 선택하셨습니다.";
}else{
	sides = "선택하지 않으셨습니다.";
}

//--총합구하기--
int totalPrice = 0;
switch (pizza) {
case "치즈피자":
	totalPrice += 5000;
	break;
case "콤비네이션피자":
	totalPrice += 6000;
	break;
case "포테이토피자":
	totalPrice += 7000;
	break;
case "고구마피자":
	totalPrice += 7000;
	break;
case "불고기피자":
	totalPrice += 8000;
	break;
default:
	break;
}
if (topping != null) {
	for (int i = 0; i < topping.length; i++) {
		switch (topping[i]) {
		case "고구마무스":
	totalPrice += 1500;
	break;
		case "콘크림무스":
	totalPrice += 1000;
	break;
		case "파인애플토핑":
	totalPrice += 2000;
	break;
		case "치즈토핑":
	totalPrice += 2000;
	break;
		case "치즈크러스트":
	totalPrice += 2000;
	break;
		case "치즈바이트":
	totalPrice += 3000;
	break;
		default:
	break;
		}

	}
}
if (side != null) {
	for (int i = 0; i < side.length; i++) {
		switch (side[i]) {
		case "오븐구이통닭":
	totalPrice += 9000;
	break;
		case "치킨스틱&윙":
	totalPrice += 4900;
	break;
		case "치즈오븐스파게티":
	totalPrice += 4000;
	break;
		case "새우링&웨지감자":
	totalPrice += 3500;
	break;
		case "갈릭포테이토":
	totalPrice += 3000;
	break;
		case "콜라":
	totalPrice += 1500;
	break;
		case "사이다":
	totalPrice += 1500;
	break;
		case "갈릭소스":
	totalPrice += 500;
	break;
		case "피클":
	totalPrice += 300;
	break;
		case "핫소스":
	totalPrice += 100;
	break;
		case "파마산 치즈가루":
	totalPrice += 100;
	break;
		default:
	break;
		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역</title>
</head>
<body>
	<h1>주문 내역</h1>
	<h2>
		피자는 <%=pizza%>, 토핑은 <%=toppings%>, 사이드는 <%=sides%>
	</h2>
	<br>
	<br>
	<br>
	<h2>
		<strong>총합 : <u><%=totalPrice%>원</u></strong>
	</h2>
	<br>
	<br>
	<h1>즐거운 식사시간 되세요~</h1>
</body>
</html>