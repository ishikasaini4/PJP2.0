
function calculation(){
    var operation = document.getElementsByName("operation").value;
    var res = NaN;
    switch(operation){
        case 1: res = add(); break;
        case 2: res = subtract(); break;
        case 3: res = minusDays(); break;
        case 4: res = minusWeeks(); break;
        case 5: res = minusMonths(); break;
        case 6: res = dayOfWeek(); break;
        case 7: res = weekOfYear(); break;
        case 8: res = nlpToDate(); break;
    }
    document.getElementById("result").innerHTML = res;
}

function add(){
    var d1 = document.getElementsByName("date1").value;
    console.log(typeof d1);
    var d2 = document.getElementsByName("date2").value;

    var date1 = new Date(d1);
    var date2 = new Date(d2);
    var add = new Date(date2.getTime() + date1.getTime());
    return (add.getUTCDate() - 1)+"-"+(add.getUTCMonth())+"-"+(add.getUTCFullYear());

}

function subtract(){
    var d1 = document.getElementsByName('date1').value;
    var d2 = document.getElementsByName('date2').value;

    var date1 = new Date(d1);
    var date2 = new Date(d2);
    var add = new Date(date2.getTime() - date1.getTime());
    return (add.getUTCDate() - 1)+"-"+(add.getUTCMonth())+"-"+(add.getUTCFullYear());
}
function minusDays(){
    var date1=document.getElementsByName('date1').value;
    var days = document.getElementsByName('days').value;
    var date = new Date(date1);
    date.setDate(date.getDay()-days);
    return (date.getUTCDate() - 1)+"-"+(date.getUTCMonth())+"-"+(date.getUTCFullYear());
}


function minusWeeks(){
    var date1=document.getElementsByName('date1').value;
    var weeks = document.getElementsByName('weeks').value;
    var date = new Date(date1);
    date.setDate(date.getDay()-(weeks*7));
    return (date.getUTCDate() - 1)+"-"+(date.getUTCMonth())+"-"+(date.getUTCFullYear());
}

function minusMonths(){
    var date1=document.getElementsByName('date1').value;
    var months = document.getElementsByName('months').value;
    var date = new Date(date1);
    date.setMonth(date.getMonth()-months);
    return (date.getUTCDate() - 1)+"-"+(date.getUTCMonth())+"-"+(date.getUTCFullYear());
}

function dayOfWeek(){
    var weekday=new Array(7);

    weekday[0]="Monday";weekday[1]="Tuesday";weekday[2]="Wednesday";
    weekday[3]="Thursday";weekday[4]="Friday";weekday[5]="Saturday";
    weekday[6]="Sunday";

    var date1=document.getElementsByName('date1').value;
    var date = new Date(date1);
    return weekday[date.getDay()];
}

function weekOfYear(){
    var date1=document.getElementsByName('date1').value;
    var date = new Date(date1);

    var Jan1 =  new Date(this.getFullYear(), 0, 1); 
    var numberOfDays = Math.floor((this - Jan1) / (24 * 60 * 60 * 1000)); 
    return Math.ceil((date.getDay() + 1 + numberOfDays) / 7); 

}


function nlpToDate(){
}

