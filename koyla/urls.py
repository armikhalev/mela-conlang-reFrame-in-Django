from django.urls import path
from . import views

urlpatterns = [
		path('words/', views.WordSet.as_view()),
		path('las/', views.LaSet.as_view()),
		path('cards/', views.CardSet.as_view()),
		path('intros/', views.IntroSet.as_view()),
		path('grammar-cards/', views.GrammarCardSet.as_view()),
		path('grammar-cards/(<pk>[0-9]+)/', views.GrammarCardDetail.as_view()),
		path('alphabets/', views.AlphabetSet.as_view()),
]
