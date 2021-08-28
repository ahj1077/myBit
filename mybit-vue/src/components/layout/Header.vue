<template>
    <div id="navbar">
        <div id="logo">
            <a href="#"><h3>MyBit</h3></a>
        </div>

        <div id="nav_list">
            <ul id="ul">
                <li><a href="/">거래소</a></li>
                <li><a href="/myCoin">나의 코인</a></li>
                <li><a href="#">김치프리미엄</a></li>
            </ul>
        </div>  
    </div>
</template>

<style>

    #navbar {       
        display: flex;
        align-items: center; 
        background-color: #093687;
        height: 60px;
        width: 100%;
    }

    #ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
    }

    #logo {
        float: left;
        padding-left: 15%;
    }

    #logo a{
        color: #FFFFFF;
        text-decoration: none;
    }

    #ul li{
        display : inline;
    }

    #ul li a{
        font-weight: bold;   
        color: #FFFFFF;
        text-decoration: none;        
        margin-left: 20px;
    }

    #ul li a:hover{    
        color: #EEEEEE;
    }

    #nav_list {
        display: flex;
        align-items: center;
        margin-left: 3%;
        height: 100%;
    }

</style>

<script>
export default {
    name: "header",
};
</script>
