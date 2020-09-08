<html>
  <head>
    <title>Hello360</title>
    <style>body { margin: 0; }</style>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="pages/css/simple_style.css">
	<script src="pages/libs/jquery.min.js"></script>
	
	<script>
		function getTextureAndBackgrounds()
		{			
			$.ajax(
			        {
			                url: "TexturePicker.action",
			                data :
			                {
			                	//motionFolder : myFolder,
			                },
			                success: function(result)
			                {
			                	var parsedResult = $.parseJSON(result);			                	
			                	var backgrounds = parsedResult.availableBackground;
								var backgroundHtml = "<label for='backgroundSelect'>Background</label><br>";
								backgroundHtml+="<select id='backgroundSelect' name='backgroundSelect' size='1'>";
			                	for (var currentBackground in backgrounds)
		                        {
			                		backgroundHtml += "<option value='" + backgrounds[currentBackground] + "'>" + backgrounds[currentBackground] + "</option>";
		                        }
			                	backgroundHtml+="</select><br>";
			                	$("#divSelectBackground").html(backgroundHtml);
			                	//Same but for Textures!
			                	var textures = parsedResult.availableTextures;
								var texturesHtml = "<label for='textureSelect'>Texture</label><br>";
								texturesHtml+="<select id='textureSelect' name='textureSelect' size='1'>";
			                	for (var currentTexture in textures)
		                        {
			                		texturesHtml += "<option value='" + textures[currentTexture] + "'>" + textures[currentTexture] + "</option>";
		                        }
			                	texturesHtml+="</select><br>";
			                	$("#divSelectTexture").html(texturesHtml);
			                }
			        }
			        );
		}
		
		function sendValuesAndRefreshIFrame()
		{
			var sBackground = $('#backgroundSelect').val();
			var sTexture = $('#textureSelect').val();
			$.ajax(
			        {
			                url: "TexturePicker.action",
			                data :
			                {
			                	selectedBackground : sBackground,
			                	selectedGirl : sTexture,
			                },
			                success: function(result)
			                {
			                	document.getElementById('some_frame_id').contentWindow.location.reload();
			                }
			        }
			        );
		}
		
		function downloadInstagramPackager()
		{			
			$.ajax(
			        {
			                url: "InstagramPackager.action",
			                data :
			                {
			                	
			                },
			                success: function(result)
			                {
			                	console.log(result);
			                }
			        }
			        );
		}
		
		
		$( document ).ready(function() {
	        console.log( "document loaded" );
	        getTextureAndBackgrounds();
	    });
	</script>
  </head>
  <body>
	  <header>
		<h2>HoloU</h2>
	  </header>
    <!-- Attachment point for your app -->
	<div>
	
		<section>
		  <nav>
			<ul>
				<li>
					<div id="divSelectBackground">No background!</div>
				</li>
			  <li>
			  		<div id="divSelectTexture">No Texture!</div>
			  </li>
			</ul>
			<button type="button" onclick='sendValuesAndRefreshIFrame()'>Refresh Render</button>
			<form method="get" action="InstagramPackager.action">
				<button type="submit">Export to Instagram</button>
			</form>
		  </nav>
		  
		  <article>
			<h1>Pijama Girl 1</h1>
			<iframe id="some_frame_id" src="indexMyIFrame.html" title="W3Schools Free Online Web Tutorials" style="
				width: 800px;
				height: 600px;
			"></iframe>	
		  </article>
		</section>

		<footer>
		  <p>Footer</p>
		</footer>

		
	</div>
    
  </body>
</html>

