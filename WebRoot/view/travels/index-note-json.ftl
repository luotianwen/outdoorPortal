 <#list travelsList as travels>
		<li>
		<div class="note-img"><a href="travels/detail/${travels.id}.html" target="_blank">
		<#if travels.cover_image == 'null' || !travels.cover_image??>
			<img src="static/images/hw_img/note-img-no.jpg" />
		<#else>
			<img src="${travels.cover_image}@!syyj" />
		</#if>
		</a></div>
		<div class="note-title"><a href="travels/detail/${travels.id}.html" limit="18" target="_blank">${travels.title}</a></div>
		<div class="note-info"><a href="users/center.html?id=${travels.user_id}" class="note-user"><img  src="${travels.uHeadImg}">${travels.userName}</a><span class="note-date">${travels.issued_time?string("yyyy-MM-dd HH:mm:ss")}</span><a href="javascript:void(0)" class="note-good">${travels.like_count}<i></i></a></div>
		<div class="note-word"><a href="travels/detail/${travels.id}.html" target="_blank">
			<#if travels.digest?length gt 69>
				${travels.digest[0..67]}……
			<#else>
				${travels.digest}
			</#if>
		</a></div>
		<div class="note-extra">
		<a href="javascript:" class="note-place"><i></i>${travels.address}</a>
		<span class="note-view"><i></i>${travels.read_count}/${travels.reply_count}</span>
		<span class="note-star"><i></i>${travels.collection_count}</span>
		</div>
		</li>
</#list >
              