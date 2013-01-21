$.getScript('js/controller/notz.js');
$.getScript('js/lib/dateutil.js');

/*
 * CONTROLLER VARIABLES
 */
var currentNote = null;
var formMode = 'CREATION'; // | EDITION

/*
 * ON INITIALIZE
 */

loadNotes();

/*
 * NOTE LIST
 */

function loadNotes() {
  console.log('loadNotes');
  console.log('close modal');

  $('#noteFormModal').modal('hide');
  
  $.ajax({
    type: 'GET',
    url: noteResource + '/' + getUserId(),
    success: function(data) {
      renderNotes(data);
    },
    error: function(data) {
      alert('error');
    },
    dataType: 'json'
  });
}

function renderNotes(data) {
  console.log('renderList');
   // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
   var list = (data == null || data.note == null) ? [] : (data.note instanceof Array ? data.note : [data.note]);

   $('#noteList .note').remove();
   $.each(list, function(index, note) {
     var title = note.title;
     var description = note.description;
     var modificationDate = new Date(note.modificationDate);
     var modificationDateHtml = modificationDate.format("dd-mm-yyyy hh:MM:ss");
     var editLinkId = 'editLink_' + index;
     var deleteLinkId = 'deleteLink_' + index;

     var noteHtml = '';
     noteHtml = noteHtml.concat('<div id="note_' + index + '" class="note">').concat("\n");
     noteHtml = noteHtml.concat('  <div class="noteHeader">').concat("\n");
     noteHtml = noteHtml.concat('    <div class="title"><strong>' + title + '</strong></div>').concat("\n");
     noteHtml = noteHtml.concat('    <div class="actions">').concat("\n");
     noteHtml = noteHtml.concat('      <a id="' + editLinkId + '" href="#noteFormModal" class="editLink" title="Editer" role="button" data-toggle="modal"></a>').concat("\n");
     noteHtml = noteHtml.concat('      <a id="' + deleteLinkId + '" href="#" class="deleteLink" title="Supprimer" ></a>').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('  </div>').concat("\n");
     noteHtml = noteHtml.concat('  <div class="noteBody">').concat("\n");
     noteHtml = noteHtml.concat('    <div class="noteDescription">').concat("\n");
     noteHtml = noteHtml.concat('      <span class="description">' + description + '</span>').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('  </div>').concat("\n");
     noteHtml = noteHtml.concat('  <div class="noteFooter">').concat("\n");
     noteHtml = noteHtml.concat('    <div class="metadata">').concat("\n");
     noteHtml = noteHtml.concat('      <span>' + modificationDateHtml + '</span>').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('    <div class="links">').concat("\n");
     noteHtml = noteHtml.concat('    </div>').concat("\n");
     noteHtml = noteHtml.concat('  </div>').concat("\n");
     noteHtml = noteHtml.concat('</div>').concat("\n");
     
     $('#noteList').append(noteHtml);
     
     $("#" + editLinkId).click(function(event) {
       editNote(note);
       event.stopPropagation();
       event.preventDefault();
     });

     $("#" + deleteLinkId).click(function() {
       deleteNote(note.id);
     });

   });
}

/*
 * NOTE FORM common behavior
 */

$("#validateNoteButton").click(function() {
  $("#noteForm").submit();
});

$("#noteForm").submit(function(event) {
  event.preventDefault();
  if (formMode == 'CREATION') {
    saveNote(noteFormToJSON());
  } else {
    updateNote(noteFormToJSON());
  }
});

//Helper function to serialize all the form fields into a JSON string
function noteFormToJSON() {
  var note = null;
  if (formMode == 'CREATION') {
    note = new Object();
  } else { // EDITION
    note = currentNote;
  }
  note.title = $('#noteFormTitle').val();
  note.description = $('#noteFormDescription').val();

  return JSON.stringify(note);
}

function getUserId() {
  return $.cookie('Notz-UserId');
}

/*
 * NOTE CREATION
 */

$("#newNoteButton").click(function(event) {
  createNote();
  event.stopPropagation();
  event.preventDefault();
});


function createNote() {
  console.log('createNote');
  currentNote = null;
  formMode = 'CREATION';
  $('#noteFormTitle').val('');
  $('#noteFormDescription').val('');
  $('#noteFormModal').modal('show');
}

function saveNote(note) {
  console.log('createNote');
  var resourcePath = noteResource + '/' + getUserId();
  $.ajax({
    type: 'POST',
    url: resourcePath,
    data: note,
    success: loadNotes,
    error: function(data) {
      alert('error');
    },
    dataType: 'json',
    contentType: 'application/json'
  });
}

/*
 * NOTE EDITION
 */

function editNote(note) {
  console.log('editNote');
  currentNote = note;
  formMode = 'EDITION';
  $('#noteFormTitle').val(note.title);
  $('#noteFormDescription').val(note.description);
  $('#noteFormModal').modal('show');
}

function updateNote(note) {
  console.log('editNote');
  var resourcePath = noteResource + '/' + getUserId();
  $.ajax({
    type: 'PUT',
    url: resourcePath,
    data: note,
    success: loadNotes,
    error: function(data) {
      alert('error');
    },
    dataType: 'json',
    contentType: 'application/json'
  });
}

/*
 * NOTE DELETE
 */

function deleteNote(noteId) {
  console.log('deleteNote');
  var resourcePath = noteResource + '/' + getUserId();
  $.ajax({
    type: 'DELETE',
    url: resourcePath,
    data: noteId,
    success: function(data) {
      loadNotes();
    },
    error: function(data) {
      alert('error');
    }
  });
}

