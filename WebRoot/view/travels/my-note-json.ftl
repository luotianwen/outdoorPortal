<#list travelsList as travels>
    <li>
       <div class="mn-img"><img src="${travels.cover_image}" /></div>
          <div class="mn-item">
            <div class="mn-actions"><a href="javascript:void(0)" class="note-good on">${travels.like_count}<i></i></a></div>
            <div class="mn-info">
              <div class="mn-tit"><h2><a href="travels/detail/${travels.id}.html" target="_blank">${travels.title}</a></h2></div>
              <div class="mn-date">${travels.issued_time?string("yyyy-MM-dd HH:mm:ss")}</div>
              <div class="mn-text"><a href="travels/detail/${travels.id}.html" target="_blank">${travels.digest}<#if travels.digest?length gt 280>……</#if></a></div>
             </div>
     		<div class="mn-extra">
            <span class="note-view"><i></i>${travels.read_count}/${travels.reply_count}</span>
            <span class="note-star"><i></i>${travels.collection_count}</span>
            <a href="javascript:void(0)" class="note-place"><i></i>${travels.address}</a>
            </div>                   
          </div>
    </li>
</#list >
  