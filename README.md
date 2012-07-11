notz
====

Notz is a tiny application in order to test some frameworks, languages, architectural & design concepts.

Features:
---------
1) As a unregister user, I can sign-up in order to become a member (a.k.a "user") and to be allowed to access the full site
2) As a user, I can login/logout
3) As a user, I can edit my profile
4) As a user, I can ask a new password generation in case of forgotten password
5) As a user, I can delete my account
6) As a user, I can consult and search my notes
7) As a user, I can manage (add/edit/delete) a note
8) As a user, I can share a note on a Social Network (Facebook, Twitter, Google+)
9) As a user, I can associate Tags to my notes in order to improve sort & search

Technical constraints:
----------------------
I.   The application must be totally modulable (separation in 4 layers : Core (Domain & External Interfaces), Services (Persistence + Business rules), WS (Web services), UI (User interface + WS consumption)
II.  Build factory configuration (Maven + GIT) and technical design concetption are as important as implementation code
