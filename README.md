# Android App Used For Managing Flight Bookings

## Requirements For Running The App

Android Stdio 3.5 or above
Firebase Account
Gmail or Any Other Mail ID and it's password
Proper Internet Connection
Target SDK Version is 29 but minimum SDK version is 21 so an android phone for running the app

## What All This Great App Can Do ?? Check it out Below....

App opens with an activity which is asking for some sort of a password. If you are a normal user who is booking a ticket you can proceed by just clicking on "Enter" but if you are an Admin you have to get yourself recoganised by entering the "CORRECT PASSWORD". If the entered password is wrong then app will consider you as a user and it will direct you to normal user interface activity else you will be directed to "Flight Management Admin Section". Lets first understand the Flight Management System i.e. it is given that you have entered the password correctly.

Password for entering in the Flight Management Activity (or the Admin Section) : 75790152222225109757

I know password is long but it has to be safe and easy to login so it is this long. Trick to remember it:- first half of it "7579015222" and just appent the reverse of the first part to the end of first part like this :- "7579015222" + (Reverse of ("7579015222")) = "7579015222" + "2225109757" = "75790152222225109757" or just 75790152222225109757. Now, as you have already logined in you will see 2 tabs. 

First,"Adding A Flight" :- Enter the required information in the given places and click on "ADD FLIGHT" button. A toast message will appear that the flight is added and you will be redirected to Admin Section. So now you can go for a Another Flight Addition or Choose Second option which is described as below.

Secord, "Deleting A Flight" :- If an case you want to delete a Flight, this is the button which will help you in doing that. After you click on this button, You will be Asked to Enter the date of Flight and Flight ID or Flight Number and that's it your flight is cancelled. It's now that easy.

So this was the Admin Section and Now Let's come to user section which will be used much more oftenly than the admin section. So close the app properly in your Android Device and now open it again like a user( basically restart the app). When, you will open the app you will be asked for enter the password but this time we are a normal user so we will just click on enter and move on. Now that you are a user who want to manage or book a new flight booking, you will have 3 buttons infront of you.

Again,

First one is to book a new flight. Enter the required information. Finally comes seat selection.Now, there could have been a ploblem that if two people are booking seats at the same time there may be a chance that both of them want to select the same seat so in that case one who has clicked the "Proceed To Payment" button first will get the seat and the other user will get notified instantaneously that the particular seat the person wanted to select has already been selected by someone else. At the end you will be asked to enter your email address and when you press will "Go", and you will recieve a "Conformation Mail". Yes, you read it right, you will recieve a conformation mail which has to be stored by the customer for future reference.

Secondly, Now that we have Booking ID, Enter your name and Booking Id and you can delete your ticket. It's that easy and safe. For this also you will get a mail of "Cancellation Conformation".

Lastly, If you have the booking ID, Enter your name and Booking Id and get your flight ticket and ticket status.

There is also a bank in database which has some virtual money. If you book a ticket of Rs 3000 then amount in bank will increase by 3000 and if you cancel a ticket Rs 3000 will be reduced from amount of bank. If a flight is cancelled and 6 seats were booked so then amount will be reduced by Rs18000.

## Author
### Archit Sangal
### IIIT Bangalore
