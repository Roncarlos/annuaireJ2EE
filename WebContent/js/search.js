$( document ).ready(function() {

  // hide form & fill
  $("#searchForm").hide();
  $("#searchFill").hide()

  // on click on buttonform
  // show the form
  // and then hide the button
  $("#searchButton").click(function(){
    $(this).hide("fast", function(){
      $("#searchForm").show("slow");
      $("#searchFill").show("fast");
    });
  });

  // on click on cross to close form
  // hide form and then show button
  $("#searchClose").click(function() {
    $("#searchForm").hide("fast", function() {
      $("#searchButton").show("slow");
      $("#searchFill").hide("fast");
    });
  });



});
