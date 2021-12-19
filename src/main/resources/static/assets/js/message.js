document.querySelector('#yubor').onclick = function(){
    let ism=document.querySelector('#ismFamiliya').value;
    let manzil=document.querySelector('#manzil').value;
    let nomer=document.querySelector('#nomer').value;
    let xabar=document.querySelector('#xabar').value;
    let message='Ism va familiya: '+ism+';%0AEmail yoki telegram account: '+manzil+';%0ATel nomer: '+nomer+';%0AXabar: '+xabar+';';    
    const token = "5077403284:AAFofH2it5Dzs2Vk6H3Qb678EL8agtXRtI8";
    const url ='https://api.telegram.org/bot'+token+'/sendMessage?chat_id=-791437981&text='
    let api = new XMLHttpRequest();
    api.open("GET",url+message,true);
    api.send();
}