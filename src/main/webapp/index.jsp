<%@  page language="java" import="java.util.*" contentType="text/html ;charset=UTF-8" %>
<!DOCTYPE html>

<html>
<head >
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <link rel="stylesheet" href="static/css/jquery.mobile-1.4.2.css">
  <title>寄收件人信息填写</title>
  <script type="text/javascript"  language="javascript">
  
  //首先需要初始化
  var   xmlDoc;   
  var   nodeIndex; 
  function   getxmlDoc()   
  { 
    try 
    {
        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");   
          var   currNode;   
          xmlDoc.async=false;   
          xmlDoc.load("Area.xml");    
    }
    catch (e)
    {
      try
      
      {
          xmlDoc=document.implement.createDocument('','',null);
            xmlDoc.load("Area.xml");     
      }
      
      catch (e)
      {
        try 
        {
          var xmlhttp=new window.XMLHttpRequest();
          xmlhttp.open("GET","Area.xml",false);
          xmlhttp.send(null);
          if (xmlhttp.readyState==4)
            { 
            xmlDoc=xmlhttp.responseXML.documentElement;
            }
          
        }
        catch(e)
        {
          alert("您的浏览器不能正常加载文件，请切换到兼容模式，或者更换浏览器")
        }
        
      }
    }

          
  }
  function Init()
  {
    //打开xlmdocm文档
    getxmlDoc();
    var   dropElement1=document.getElementById("Select1"); 
    var   dropElement2=document.getElementById("Select2"); 
    var   dropElement3=document.getElementById("Select3");  
    var   dropElement4=document.getElementById("Select4"); 
    var   dropElement5=document.getElementById("Select5"); 
    var   dropElement6=document.getElementById("Select6");  
    RemoveDropDownList(dropElement1);
    RemoveDropDownList(dropElement2);
    RemoveDropDownList(dropElement3);
    RemoveDropDownList(dropElement4);
    RemoveDropDownList(dropElement5);
    RemoveDropDownList(dropElement6);

    var  TopnodeList=xmlDoc.selectSingleNode("address").childNodes;
    if(TopnodeList.length>0)
    {
        //省份列表
        var country;
        var province;
        var city;
        for(var   i=0; i<TopnodeList.length;   i++)
        {
              //添加列表项目
              country=TopnodeList[i];       
              var   eOption=document.createElement("option");   
              eOption.value=country.getAttribute("name");
              eOption.text=country.getAttribute("name");
              dropElement1.add(eOption);
        }
        for(var   i=0; i<TopnodeList.length;   i++)
        {
              //添加列表项目
              country=TopnodeList[i];       
              var   eOption=document.createElement("option");   
              eOption.value=country.getAttribute("name");
              eOption.text=country.getAttribute("name");
              dropElement4.add(eOption);
        }
        if(TopnodeList[0].childNodes.length>0)
        {
            //城市列表
            for(var i=0;i<TopnodeList[0].childNodes.length;i++)
            {
               var   id=dropElement1.options[0].value;
               //默认为第一个省份的城市
               province=TopnodeList[0]; 
               var   eOption=document.createElement("option");  
               eOption.value=province.childNodes[i].getAttribute("name");   
               eOption.text=province.childNodes[i].getAttribute("name");   
               dropElement2.add(eOption);
            }
            for(var i=0;i<TopnodeList[0].childNodes.length;i++)
            {
               var   id=dropElement4.options[0].value;
               //默认为第一个省份的城市
               province=TopnodeList[0]; 
               var   eOption=document.createElement("option");  
               eOption.value=province.childNodes[i].getAttribute("name");   
               eOption.text=province.childNodes[i].getAttribute("name");   
               dropElement5.add(eOption);
            }
            if(TopnodeList[0].childNodes[0].childNodes.length>0)
            {
               //县列表
               for(var i=0;i<TopnodeList[0].childNodes[0].childNodes.length;i++)
               {
                  var   id=dropElement2.options[0].value;
                  //默认为第一个城市的第一个县列表
                  city=TopnodeList[0].childNodes[0];  
                  var   eOption=document.createElement("option");  
                  eOption.value=city.childNodes[i].getAttribute("name");   
                  eOption.text=city.childNodes[i].getAttribute("name");   
                  this.document.getElementById("Select3").add(eOption);
               }
               for(var i=0;i<TopnodeList[0].childNodes[0].childNodes.length;i++)
               {
                  var   id=dropElement5.options[0].value;
                  //默认为第一个城市的第一个县列表
                  city=TopnodeList[0].childNodes[0];  
                  var   eOption=document.createElement("option");  
                  eOption.value=city.childNodes[i].getAttribute("name");   
                  eOption.text=city.childNodes[i].getAttribute("name");   
                  this.document.getElementById("Select6").add(eOption);
               }
            }
        }
    }
  }   
  function   selectCity()   
  {       var   dropElement1=document.getElementById("Select1"); 
          var   name=dropElement1.options[dropElement1.selectedIndex].value;
          //alert(id);
          var   countryNodes=xmlDoc.selectSingleNode('//address/province [@name="'+name+'"]');   
          //alert(countryNodes.childNodes.length); 
          var   province=document.getElementById("Select2");       
          var   city=document.getElementById("Select3");       
          RemoveDropDownList(province);   
          RemoveDropDownList(city);
          if(countryNodes.childNodes.length>0)
          {
               //填充城市          
               for(var   i=0;   i<countryNodes.childNodes.length;   i++)   
               {   
                  var   provinceNode=countryNodes.childNodes[i];     
                  var   eOption=document.createElement("option");   
                  eOption.value=provinceNode.getAttribute("name");   
                  eOption.text=provinceNode.getAttribute("name");   
                  province.add(eOption);   
               }
               if(countryNodes.childNodes[0].childNodes.length>0)
               {
                  //填充选择省份的第一个城市的县列表
                  for(var i=0;i<countryNodes.childNodes[0].childNodes.length;i++)
                  {
                      //alert("i="+i+"\r\n"+"length="+countryNodes.childNodes[0].childNodes.length+"\r\n");
                      var   dropElement2=document.getElementById("Select2"); 
                      var   dropElement3=document.getElementById("Select3"); 
                      //取当天省份下第一个城市列表
                      var cityNode=countryNodes.childNodes[0];
                      //alert(cityNode.childNodes.length); 
                      var   eOption=document.createElement("option");  
                      eOption.value=cityNode.childNodes[i].getAttribute("name");   
                      eOption.text=cityNode.childNodes[i].getAttribute("name");   
                      dropElement3.add(eOption);
                  }
               }
          }
  } 
  
  function   selectCity1()   
  {       var   dropElement1=document.getElementById("Select4"); 
          var   name=dropElement1.options[dropElement1.selectedIndex].value;
          //alert(id);
          var   countryNodes=xmlDoc.selectSingleNode('//address/province [@name="'+name+'"]');   
          //alert(countryNodes.childNodes.length); 
          var   province=document.getElementById("Select5");       
          var   city=document.getElementById("Select6");       
          RemoveDropDownList(province);   
          RemoveDropDownList(city);
          if(countryNodes.childNodes.length>0)
          {
               //填充城市          
               for(var   i=0;   i<countryNodes.childNodes.length;   i++)   
               {   
                  var   provinceNode=countryNodes.childNodes[i];     
                  var   eOption=document.createElement("option");   
                  eOption.value=provinceNode.getAttribute("name");   
                  eOption.text=provinceNode.getAttribute("name");   
                  province.add(eOption);   
               }
               if(countryNodes.childNodes[0].childNodes.length>0)
               {
                  //填充选择省份的第一个城市的县列表
                  for(var i=0;i<countryNodes.childNodes[0].childNodes.length;i++)
                  {
                      //alert("i="+i+"\r\n"+"length="+countryNodes.childNodes[0].childNodes.length+"\r\n");
                      var   dropElement2=document.getElementById("Select5"); 
                      var   dropElement3=document.getElementById("Select6"); 
                      //取当天省份下第一个城市列表
                      var cityNode=countryNodes.childNodes[0];
                      //alert(cityNode.childNodes.length); 
                      var   eOption=document.createElement("option");  
                      eOption.value=cityNode.childNodes[i].getAttribute("name");   
                      eOption.text=cityNode.childNodes[i].getAttribute("name");   
                      dropElement3.add(eOption);
                  }
               }
          }
  }
  
  function   selectCountry()   
  {   
          var   dropElement2=document.getElementById("Select2");   
          var   name=dropElement2.options[dropElement2.selectedIndex].value;   
          var   provinceNode=xmlDoc.selectSingleNode('//address/province/city[@name="'+name+'"]');   
          var   city=document.getElementById("Select3");       
          RemoveDropDownList(city);   
          for(var   i=0;   i<provinceNode.childNodes.length;   i++)   
          {   
                  var   cityNode=provinceNode.childNodes[i];     
                  var   eOption=document.createElement("option");   
                  eOption.value=cityNode.getAttribute("name");   
                  eOption.text=cityNode.getAttribute("name");   
                  city.add(eOption);   
          }   
  }
  function   selectCountry1()   
  {   
          var   dropElement2=document.getElementById("Select5");   
          var   name=dropElement2.options[dropElement2.selectedIndex].value;   
          var   provinceNode=xmlDoc.selectSingleNode('//address/province/city[@name="'+name+'"]');   
          var   city=document.getElementById("Select6");       
          RemoveDropDownList(city);   
          for(var   i=0;   i<provinceNode.childNodes.length;   i++)   
          {   
                  var   cityNode=provinceNode.childNodes[i];     
                  var   eOption=document.createElement("option");   
                  eOption.value=cityNode.getAttribute("name");   
                  eOption.text=cityNode.getAttribute("name");   
                  city.add(eOption);   
          }   
  } 
  function   RemoveDropDownList(obj)   
  {   
      if(obj)
      {
          var   len=obj.options.length;   
          if(len>0)
          {
            //alert(len);   
            for(var   i=len;i>=0;i--)   
            {   
                  obj.remove(i);   
            }
          }
       }
            
  }   
  
  </script>
</head>

<body onload="Init();">

  <script type="text/javascript" src="static/js/jquery.min.js"></script>
  <script type="text/javascript" src="static/js/jquery.mobile-1.4.2.min.js"></script>
  <script type="text/javascript"></script>

  <form action="/ytw/info" method="post" id="form1" >
    <div data-role="fieldcontain" style="vertical-align: middle">
      <table>
        <tr>
          <td>
            <label for="sender">寄件人</label>
          </td>
          <td>
            <input type="text" name="sender" />
          </td>
        </tr>
        <tr>
          <td>
            <label for="senderphone">联系方式</label>
          </td>
          <td>
            <input type="text" name="senderphone" />
          </td>
        </tr>
        <tr>
          <td>
            <label for="idnum">身份证号</label>
          </td>
          <td>
            <input type="text" name="idnum" />
          </td>
        </tr>
        <tr>
          <td>
            <label for="Select4">寄件地址</label>
          </td>
          <td>

            <select id="Select4" name="Select4" runat="server" onchange="selectCity1();">
              <option value="" selected="true">省/直辖市</option>
            </select>
            <select id="Select5" name="Select5" runat="server" onchange="selectCountry1()">
              <option value="" selected="true">请选择</option>
            </select>
            <select id="Select6" name="Select6" runat="server" >
              <option value="" selected="true">请选择</option>
            </select>
          </td>
        </tr>
                  <tr>
            <td>
              <label for="addr1">详细地址</label>
            </td>
            <td>
              <input type="text" name="addr1" />
            </td>
          </tr>

        <tr>
          <td>
            <label for="recipient">收件人</label>
          </td>
          <td>
            <input type="text" name="recipient" />
          </td>
        </tr>
        <tr>
          <td>
            <label for="recipientphone">联系方式</label>
          </td>
          <td>
            <input type="text" name="recipientphone" />
          </td>
        </tr>
        <tr>
          <td>
            <label for="Select1">收件地址</label>
          </td>
      
     

          <td>

            <select id="Select1" name="Select1" runat="server" onchange="selectCity();">
              <option value="" selected="true">省/直辖市</option>
            </select>
            <select id="Select2" name="Select2" runat="server" onchange="selectCountry()">
              <option value="" selected="true">请选择</option>
            </select>
            <select id="Select3" name="Select3" runat="server" >
              <option value="" selected="true">请选择</option>
            </select>
          </td>
        </tr>
            <tr>
            <td>
              <label for="addr2">详细地址</label>
            </td>
            <td>
              <input type="text" name="addr2" />
            </td>
            </tr>

      </table>
      <tr>
        <input type="submit" value="完成" />
      </tr>
    </div>
  </form>
</html>