
const usnmInp = document.querySelector('#username_inp');
const usnmDisplay = document.querySelector('#username_disp');


usnmInp.addEventListener('onInput', function(e){
	let usnm = e.target.value;
	usnmDisplay.innerHTML = "Welcome " + usnm;
})