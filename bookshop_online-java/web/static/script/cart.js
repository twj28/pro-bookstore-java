function editCart(cartItemId,buyCount){
    if (buyCount >0){
    window.location.href="cart.do?operate=editCart&cartItemId="+cartItemId + "&buyCount=" + buyCount;
    }
}
window.onload = function (){
    let vue = new Vue({
        el:"#cart_div",
        data:{
            cart:{}
        },
        methods:{
            getCart:function (){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'cartInfo'
                    }

                })
                    .then(function (response){
                        vue.cart=response.data;

                        console.log(response);
                        console.log(response.data);

                    })
                    .catch(function (error){})
            },
            editCart:function (cartItemId,buyCount){
                if(buyCount > 0){
                    axios({
                        method:"POST",
                        url:"cart.do",
                        params: {
                            operate:"editCart",
                            cartItemId:cartItemId,
                            buyCount:buyCount
                        }
                    })
                        .then(function (response){
                            vue.getCart();
                        })
                        .catch(function (error){});
                }
            }

        },
        mounted:function (){
            this.getCart();
        }
    });



}