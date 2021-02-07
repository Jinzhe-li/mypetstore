<jsp:include page="../common/IncludeTop.jsp" flush="true"/>
<script src="js/jquery-3.5.1.js" ></script>
<script>
    $(function () {
        var xOffset = 10;
        var yOffset = 300;
        $("area.preview").hover(
            function (e){
                if(this.id == 'birds')
                {
                    $("body").append("<p id='previews' style='position: absolute;z-index: 99;left: 62%;bottom: auto;top: 350px; background-color: #e2e2e2;white-space: pre-wrap'>category:birds" +
                        "\n" +
                        "productId=AV-SB-02" +
                        "</p>");
                }
                if(this.id=="fish"){
                    $("body").append("<p id='previews' style='position: absolute;z-index: 99;left: 44%;bottom: auto;top: 350px; background-color: #e2e2e2;white-space: pre-wrap'>category:fish" +
                        "\n" +
                        "productId=FI-FW-01" +
                        "</p>");
                }
                if(this.id=="dogs"){
                    $("body").append("<p id='previews' style='position: absolute;z-index: 99;left: 46%;bottom: auto;top: 390px; background-color: #e2e2e2;white-space: pre-wrap'>category:dogs" +
                        "\n" +
                        "productId=K9-PO-02" +
                        "</p>");
                }
                if(this.id == 'reptiles')
                {
                    $("body").append("<p id='previews' style='position: absolute;z-index: 99;left: 53%;bottom: auto;top: 470px; background-color: #e2e2e2;white-space: pre-wrap'>category:reptiles" +
                        "\n" +
                        "productId=RP-LI-02" +
                        "</p>");
                }
                if(this.id=="cats"){
                    $("body").append("<p id='previews' style='position: absolute;z-index: 99;left: 60%;bottom: auto;top: 390px; background-color: #e2e2e2;white-space: pre-wrap'>category:cats" +
                        "\n" +
                        "productId=FL-DLH-02" +
                        "</p>");
                }
            }, function() {
                $("#previews").remove();
            }
        )
    })
</script>
<div id="Welcome">
    <div id="WelcomeContent">
        Welcome to MyPetStore!
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="viewCategory?categoryId=FISH"><img src="${pageContext.request.contextPath}/images/fish_icon.gif" /></a>
            <br/> Saltwater, Freshwater <br/>
            <a href="viewCategory?categoryId=DOGS"><img src="${pageContext.request.contextPath}/images/dogs_icon.gif" /></a>
            <br /> Various Breeds <br />
            <a href="viewCategory?categoryId=CATS"><img src="${pageContext.request.contextPath}/images/cats_icon.gif" /></a>
            <br /> Various Breeds, Exotic Varieties <br />
            <a href="viewCategory?categoryId=REPTILES"><img src="${pageContext.request.contextPath}/images/reptiles_icon.gif" /></a>
            <br /> Lizards, Turtles, Snakes <br />
            <a href="viewCategory?categoryId=BIRDS"><img src="${pageContext.request.contextPath}/images/birds_icon.gif" /></a>
            <br /> Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250" href="viewCategory?categoryId=BIRDS" shape="rect" class="preview" >
                <area alt="Fish" coords="2,180,72,250" href="viewCategory?categoryId=FISH" shape="rect" class="preview" id="fish"/>
                <area alt="Dogs" coords="60,250,130,320" href="viewCategory?categoryId=DOGS" shape="rect" class="preview" id="dogs"/>
                <area alt="Reptiles" coords="140,270,210,340" href="viewCategory?categoryId=REPTILES" shape="rect" class="preview" id="reptiles"/>
                <area alt="Cats" coords="225,240,295,310" href="viewCategory?categoryId=CATS" shape="rect" class="preview" id="cats"/>
                <area alt="Birds" coords="280,180,350,250" href="viewCategory?categoryId=BIRDS" shape="rect" class="preview" id="birds"/>
            </map>
            <img height="355" src="${pageContext.request.contextPath}/images/splash.gif" align="middle" usemap="#estoremap" width="350" />
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
</div>

<%@include file="../common/IncludeBottom.jsp"%>