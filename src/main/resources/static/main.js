var $money = $('.money');
var $error = $('.error-bills');
var $errorTransf = $('.error-transfer');

/* поповнити свій рахунок*/
$('.btn').on('click', function () {
    var money = $('.refMoney').val();
    console.log(money);
    $.ajax({
        url:'/selfRefill/',
        data: {sum: money},
        type: 'put',
        success: function (result) {
           resultFromControllerSuccess(result);
        },
        error: function (error) {
            resultFromControllerError();
        }
    })
});

/*зняти гроші зі свого рахунку*/
$('.btn2').on('click', function () {
    var money = $('.getMoney').val();
    console.log(money);
    $.ajax({
        url:'/getMoney/',
        data: {sum: money},
        type: 'put',
        success: function (result) {
            resultFromControllerSuccess(result)
        },
        error: function (error) {
           resultFromControllerError();
        }
    })
});

/*Поповнити рахунок іншого коритувача*/

$('.btn3').on('click', function () {
    var money = $('.sendMoney').val();
    var idCreditCard = $('.idCredit').val();
    if (money!="" && idCreditCard!=""){
        $.ajax({
            url:'/transferMoney/',
            data: {money: money, idCard: idCreditCard},
            type: 'put',
            success: function (result) {
                $.each(result, function (k, v) {
                    if (k === 'success') {
                        $money.empty();
                        $money.append(v);
                    } else if(k === 'error') {
                        $errorTransf.empty();
                        $errorTransf.append(v)
                    }
                });
            }
        })
    }
});

$(document).on('click',function () {
    $errorTransf.empty();
    $error.empty();
});


/*functions*/
function resultFromControllerSuccess(result) {
    $.each(result, function (k, v) {
        console.log(k + " " + v);
        if (k === 'result') {
            $money.empty();
            $money.append(v);
        } else if (k === 'error'){
            $error.empty();
            $error.append(v)
        }
    });
}

function resultFromControllerError() {
    $error.empty();
    $error.append("wrong enter moneys")
}