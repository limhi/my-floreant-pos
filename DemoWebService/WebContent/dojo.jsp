<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Demo</title>
<link href="manage/style.css" rel="stylesheet" type="text/css" />
<link rel="StyleSheet" type="text/css"
	href="dojo-1.7.2/dijit/themes/tundra/tundra.css">
<link rel="StyleSheet" type="text/css"
	href="dojo-1.7.2/dojox/grid/enhanced/resources/claro/EnhancedGrid.css">
<link rel="StyleSheet" type="text/css"
	href="dojo-1.7.2/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css">
</head>
<script type="text/javascript" src="dojo-1.7.2/dojo/dojo.js"
	djConfig="parseOnLoad:true"></script>
<script type="text/javascript">
            dojo.require("dojo.parser");
            dojo.require("dojox.grid.EnhancedGrid");
            dojo.require("dojox.grid.enhanced.plugins.Pagination");
            dojo.require("dojo.data.ItemFileWriteStore");
</script>
<script>
var grid;
var store;

dojo.ready(function(){
	dojo.xhrPost({ url : 'load.do',
        handleAs: "json",
		load : function(response, ioArgs) {
			showGrid(response);
		}});
});

function showGrid(json){
    var data = {
  	      items: json
  	    };

store = new dojo.data.ItemFileWriteStore({data: data});
var layout = [[
       	    {name: '編號', field: 'id',width: "40px",hidden:true},
       		{name: '機器編號', field: 'mnum',width: "auto",editable: true},
            {name: '事件編號', field: 'eventId',width: "auto",editable: true},
            {name: '事件時間', field: 'eventTime',width: "atuo",editable: true},
            {name: '金額', field: 'billIn',width: "auto",editable: true},
            {name: '一百', field: 'note1',width: "auto",editable: true},
            {name: '伍佰', field: 'note2',width: "auto",editable: true},
            {name: '一千', field: 'note3',width: "auto",editable: true},
            {name: '兩千', field: 'note4',width: "auto",editable: true},
            
          ]];
	    
 grid = new dojox.grid.EnhancedGrid({
 id: 'grid',
 store: store,
 structure: layout,
 rowSelector: '20px',
 clearOnClose:true,
 plugins: {
    pagination: {
       pageSizes: ["25", "50", "100", "All"],
       description: true,
       sizeSwitch: true,
       pageStepper: true,
       gotoButton: true,
       defaultPageSize:"20",
       maxPageStep: 4,
       position: "top"
   }
 }
}, document.createElement('div'));

dojo.byId("gridDiv").appendChild(grid.domNode);
grid.startup();

dojo.connect(grid, "onApplyCellEdit", grid, function(inValue,inRowIndex,inFieldIndex){
	//alert('value:'+inValue+' rowIdx:'+inRowIndex+' inFieldIndex:'+inFieldIndex);
    var items = this.selection.getSelected();
    var id;
    dojo.forEach(items, function(item){
        id = this.store.getValue(item, "id");
        //alert(id);
    }, this);
	dojo.xhrPost({ url : 'update.do?id='+id+'&type='+inFieldIndex+'&value='+inValue,
        handleAs: "json",
		load : function(response, ioArgs) {
		}});
});

}
function delSelected(){
	var msg;
	  msg = "您確定要刪除嗎?";
	  x= confirm(msg);
	  if(x){
	var items = grid.selection.getSelected();
	var id;
	    dojo.forEach(items, function(item){
	        id = store.getValue(item, "id");
	        //delete from table
	        store.deleteItem(item);
	    	//delete from db
	    	dojo.xhrPost({ url : 'delete.do?id='+id,
	            handleAs: "json",
	    		load : function(response, ioArgs) {
	    		}});
	    }, this);
	  }
}
function add(){
    
	var addNewItem = {id: "Default", mnum: "mnum", eventId: "eventId",eventTime:"eventTime",billIn:"billIn",note1:"note1",note2:"note2",note3:"note3",note4:"note4"};
    //add to table
	store.newItem(addNewItem);
    //add to db
	dojo.xhrPost({ url : 'update.do',
        handleAs: "json",
		load : function(response, ioArgs) {
		}});
}

</script>
<style type='text/css'>
#grid {
    width: 45em;
    height: 40em;
}
</style>

<body class="tundra">
	
	
		

			<div class="left">
				<h2>DEMO</h2>
<input id="delBtn" name="delBtn" type="button" onclick="delSelected()" value="刪除選取" style="width: 100px">
<input id="addBtn" name="addBtn" type="button" onclick="add()" value="新增" style="width: 100px">
				<form name="newsForm" action="newsMgrMain.do" method="post">
					<div id="gridDiv" style="font-size: 12pt"></div>
 </p>
				</form>
			</div>
		
	
	<div class="footer">
		<div class="footerinner"></div>

	</div>
	<div class="footerbottom">
		
	</div>
	<script>
thisform=document.newsForm;
</script>
</body>
</html>
